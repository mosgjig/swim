FROM java:8
VOLUME /tmp
ADD swim-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xdebug","-Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=5005","-jar","/app.jar"]
