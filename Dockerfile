FROM amazoncorretto:17
VOLUME /tmp
EXPOSE 8443
COPY target/library-1.0.jar library-1.0.jar
CMD ["java", "-jar", "library-1.0.jar"]
