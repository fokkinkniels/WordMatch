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

  //General Logic

  // fetch online users
  const users = [];
  for (let [id, socket] of io.of("/").sockets) {
    users.push({
      userID: id,
      username: socket.username,
    });
  }
  socket.emit("users", users);

  // notify online users
  socket.broadcast.emit("user connected", {  userID: socket.id,  username: socket.username,  });

  // notify users upon disconnection
  socket.on("disconnect", () => {  socket.broadcast.emit("user disconnected", socket.id);  });


  //Room Logic

  // invite player to room
  socket.on("invite", ( data ) => {
    socket.to(data).emit("invite", { from: socket.id });

    console.log( data + " " + socket.id)
    socket.room = socket.id
    socket.join(socket.room);
  });

  // accept invite from player
  socket.on("accept invite", ( roomId ) => {
    console.log(roomId)
    socket.room = roomId
    socket.join(socket.room)
    io.to(socket.room).emit(socket.username + " Joined the room")
  })

  // Send room messages to all members in that room
  socket.on("room message", (data) => {  io.to(socket.room).emit({from: socket.username, message: data})  })

});

const PORT = process.env.PORT || 3000;

httpServer.listen(PORT, () =>
  console.log(`server listening at http://localhost:${PORT}`)
);