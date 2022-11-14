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
    if(socket.room == undefined){
      socket.room = socket.id
      socket.to(data).emit("invite", { from: socket.id });  //create new room
    }
    else{
      socket.to(data).emit("invite", { from: socket.room})  //invite for existing room
    }
  });

  // accept invite from player
  socket.on("accept invite", ( roomId ) => {
    socket.room = roomId
    io.to(socket.id).to(roomId).emit("accept invite", roomId)
  })

  //Join Room after invite has been accepted
  socket.on("join room", data => {
    console.log("Joined room: " + socket.room + " " + socket.username)
    socket.join(socket.room);
    io.to(socket.room).emit("room connect", socket.id)
  })

  // Send room messages to all members in that room
  socket.on("room message", (data) => {  io.to(socket.room).emit("room message", {from: socket.username, message: data})  })

  // Send notification to all members in the room that user leaves
  socket.on("room disconnect", data => {
      socket.to(socket.room).emit("room disconnect", socket.id)
      socket.leave(socket.room)
      socket.room = undefined
      console.log(socket.username + " is now leaving Room and their room is now: " + socket.room)
  })

});

const PORT = process.env.PORT || 3000;

httpServer.listen(PORT, () =>
  console.log(`server listening at http://localhost:${PORT}`)
);