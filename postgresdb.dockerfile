 FROM postgres:15.0-alpine
LABEL maintainer="Evandro Cunha <evandrocunhajunior@gmail.com>"
ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=509680
ENV POSTGRES_DB=library_api
EXPOSE 3306