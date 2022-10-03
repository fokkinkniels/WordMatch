const { isObject } = require("util");

const Express = require("express")();
const Http = require("http").Server(Express);
const Socketio= require('socket.io')(Http, {
    cors: {
      origin: '*',
    }
  });

Socketio.on("connection", (socket) => {
    socket.username = "None"

    socket.on("message", (msg) => Socketio.emit("message", { 'user': socket.username, 'message': msg }))

    socket.on("join", data => {
        if (data != null) {
            socket.username = data;
        }
        Socketio.emit("message", { 'user': 'Server', 'message': socket.username + ' has joined the Server!'})
    });
});

Http.listen(3000, () => {
    console.log('Listening at: 3000...');
});