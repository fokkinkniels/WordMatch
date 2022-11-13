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

    socket.on("join", (data) => socket.broadcast.emit("message", { 'user': socket.username, 'message': 'joined' }))

    socket.on("leave", (data) => socket.broadcast.emit("message", { 'user': socket.username, 'message': 'left' }))

    socket.on("intite player", (data) => { socket.to(data).emit("incoming invite", socket.id)})
        
});

Io.on("disconnect", socket =>{
  console.log(socket.id)
})

Http.listen(port, () => {
    console.log('Listening at: ' + port + '...');
});