sudo docker stop mongo
sudo docker rm mongo
sudo docker run --name mongo -d -p 27017:27017 -v ~/data:/data/db mongo