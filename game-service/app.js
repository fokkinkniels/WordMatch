const Express = require("express")();
const Http = require("http").Server(Express);
const Io= require('socket.io')(Http, {
    cors: {
      origin: '*',
    }
  });

var port = 3000;


Io.on("connection", (socket) => {
    console.log("New connection with id:" + socket.id)
    socket.username = socket.id

    socket.on("message", (msg) => Io.emit("message", { 'user': socket.username, 'message': msg }))

    socket.on("private message", ({ content, to }) => {
      socket.to(to).emit("private message", {
        content,
        from: socket.id,
      });
    });

    socket.on("setUser", (data) => {
      socket.username = data.username
      socket.id = data.id
    })

    socket.on("join", (data) => socket.broadcast.emit("message", { 'user': socket.username, 'message': 'joined' }))

    socket.on("leave", (data) => socket.broadcast.emit("message", { 'user': socket.username, 'message': 'left' }))
        
});

Http.listen(port, () => {
    console.log('Listening at: 3000...');
});