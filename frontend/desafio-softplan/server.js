// server.js
let express = require('express');
// let path = require('path');
let serveStatic = require('serve-static');
let app = express();

app.use(serveStatic(__dirname + "/dist"));
let port = process.env.PORT || 5000;
app.listen(port);
console.log('server started '+ port);