FROM openjdk:17
LABEL maintainer = "Evandro C <evandrocunhajunior@gmail.com>"
COPY /target /usr/src
WORKDIR /usr/src
RUN java -jar library-1.0.jar
#&& -jar library-1.0.jar
EXPOSE 8000