FROM mysql:8.0.31-debian
LABEL maintainer="Evandro Cunha <evandrocunhajunior@gmail.com>"
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=509680
ENV MYSQL_ROOT_PASSWORD=509680
ENV MYSQL_DATABASE=library_api
EXPOSE 3306