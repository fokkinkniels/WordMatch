const httpServer = require("http").createServer();
const io = require("socket.io")(httpServer, {
  cors: {
    origin: "http://localhost:8080",
  },
});


io.use((socket, next) => {
  const username = socket.handshake.auth.username;
  const id = socket.handshake.auth.id;

  if (!username && !id) {
    return next(new Error("invalid username or id"));
  }
  socket.username = username;
  socket.id = id;
  next();
});

io.on("connection", (socket) => {

  console.log("New connection: \n   Username: "+ socket.username + " \n   Id: " + socket.id)

  // fetch existing users
  const users = [];
  for (let [id, socket] of io.of("/").sockets) {
    users.push({
      userID: id,
      username: socket.username,
    });
  }
  socket.emit("users", users);

  // notify existing users
  socket.broadcast.emit("user connected", {
    userID: socket.id,
    username: socket.username,
  });

  socket.on("invite", ( to ) => {
    socket.to(to).emit("invite", { from: socket.id });
    
    socket.room = socket.id
    socket.join(socket.room);
    console.log(socket.room)

  });

  socket.on("accept invite", ( roomId ) => {
    socket.room = roomId

    socket.join(socket.room)
    //console.log(socket.room)
    io.to(socket.room).emit(socket.username + " Joined the room")
  })


  // notify users upon disconnection
  socket.on("disconnect", () => {
    socket.broadcast.emit("user disconnected", socket.id);
  });
});

const PORT = process.env.PORT || 3000;

httpServer.listen(PORT, () =>
  console.log(`server listening at http://localhost:${PORT}`)
);