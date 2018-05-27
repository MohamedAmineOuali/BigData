const express = require('express'),
    app = express(),
    port = process.env.PORT || 3000;
const server = require('http').Server(app);
const io = require('socket.io')(server);



app.get('/', function(req, res) {
    res.sendfile(__dirname + '/index.html');
});



const kafka = require('kafka-node');
const ConsumerGroup = kafka.Consumer;
const client = new kafka.Client("172.19.0.2:2181");

const consumer = new ConsumerGroup(
    client,
    [
        { topic: 'spark-node'}
    ],
    {
        autoCommit: true,
        fetchMaxWaitMs: 1000,
        fetchMaxBytes: 1100000000,
        encoding: "buffer"
    }
);

consumer.on('message', function (message) {
    var data = JSON.parse(message.value);
    console.log(data);
    io.sockets.emit('channel', data);
});


consumer.on('error', function (err) {console.log(err);});
consumer.on('offsetOutOfRange', function (err) {console.log(err);});



// Turn on that server!
server.listen(port, () => {
    console.log('App listening on port 3000');
});



