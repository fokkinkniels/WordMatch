const { isObject } = require("util");

const Mongoose = require('mongoose');
const MongoDB = "mongodb+srv://fokkinkniels:T5gznS8IfBjDgPfp@woordmatch.xlblxcm.mongodb.net/message-database?retryWrites=true&w=majority";
const Msg = require('./models/message.js');

const Express = require("express")();
const Http = require("http").Server(Express);
const Socketio= require('socket.io')(Http, {
    cors: {
      origin: '*',
    }
  });

Mongoose.connect(MongoDB).then(()=>{
    console.log("Connected", {useNewUrlParser: true, useUnifiedTopology: true})
}).catch(err => { console.log(err)})

Socketio.on("connection", (socket) => {
    Msg.find().then((result) => {
        socket.emit('output-messages', result);
    })
    socket.username = "None"

    socket.on("message", (msg) => {
        msg = { 'user': socket.username, 'msg': msg }
        const message = new Msg(msg);
        message.save().then(() => {
            Socketio.emit("message", msg) 
        })});

    socket.on("join", data => {
        if (data != null) {
            socket.username = data;
        }
        Socketio.emit("message", { 'user': 'Server', 'msg': socket.username + ' has joined the Server!'})
    });
});

Http.listen(3000, () => {
    console.log('Listening at: 3000...');
});