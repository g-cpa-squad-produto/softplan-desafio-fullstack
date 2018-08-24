FROM nginx:latest
MAINTAINER Danilo Paixao
COPY /client/dist/sistemaprocesso /var/www/public
COPY /docker/config/nginx.conf /etc/nginx/nginx.conf
COPY /docker/config/ssl/cert.key /etc/nginx/ssl/cert.key
COPY /docker/config/ssl/cert.crt /etc/nginx/ssl/cert.crt
RUN chmod 755 -R /var/www/public
EXPOSE 80 443
ENTRYPOINT ["nginx"]
# Parametros extras para o entrypoint
CMD ["-g", "daemon off;"]
