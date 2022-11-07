const Express = require("express")();
const Http = require("http").Server(Express);
const Socketio= require('socket.io')(Http, {
    cors: {
      origin: '*',
    }
  });

var port = 3000;

Socketio.on("connection", (socket) => {
    console.log("New connection with id:" + socket.id)
    socket.username = "None"
});

Http.listen(port, () => {
    console.log('Listening at: 3000...');
});