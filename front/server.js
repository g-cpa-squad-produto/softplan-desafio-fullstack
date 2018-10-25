var express = require('express');
var path = require('path');
var app = express();
 
 
const port = process.env.PORT||80;
 
//Set static Folder
app.use(express.static(path.join(__dirname + '/dist')));
 
//Index routing 
app.get('/*', (req, res)=>{
    res.sendFile(path.join(__dirname + '/dist/index.html'));
});
 
app.listen(port, function(){
    console.log('Server started on port', port);
});
