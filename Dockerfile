FROM openjdk:latest
COPY build/libs /jar
WORKDIR /jar
RUN mv *.jar chat_server.jar
ENTRYPOINT [ "java" ]
CMD [ "-jar", "/jar/chat_server.jar"]
