FROM nginx:stable-alpine

RUN apk update \
    && apk -f --update --no-cache --repository http://dl-4.alpinelinux.org/alpine/edge/testing add \
    bash \
    gzip \
    && rm -rf /tmp/*

COPY nginx/nginx.conf /etc/nginx/
COPY nginx/app.conf /etc/nginx/conf.d/

RUN mkdir -p /var/www/html
WORKDIR /var/www/html

COPY dist/ ./

ENTRYPOINT ["nginx", "-g", "daemon off;"]
