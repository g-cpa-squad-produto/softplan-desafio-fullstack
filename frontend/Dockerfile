FROM node:10 AS builder

WORKDIR /app_build

ADD . .

RUN npm install -g @angular/cli@7.0.4 && npm install && ng build --prod

FROM nginx:1.13

RUN echo "America/Sao_Paulo" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata

COPY --from=builder /app_build/dist/frontend /usr/share/nginx/html

# docker build -t frontend .
# docker run -it --rm -p 8081:80 frontend
