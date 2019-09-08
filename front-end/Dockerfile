FROM node:latest

RUN mkdir /opt/app
RUN mkdir /opt/app/src

COPY start-node /usr/local/bin/

RUN chmod a+x /usr/local/bin/start-node

COPY babel.config.js /opt/app
COPY package.json /opt/app
COPY vue.config.js /opt/app
COPY src/ /opt/app/src/

WORKDIR /opt/app

RUN npm install

EXPOSE 3001

ENTRYPOINT ["start-node"]







