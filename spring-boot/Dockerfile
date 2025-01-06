FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

ADD target/todo.jar todo.jar

EXPOSE 8080
ENTRYPOINT [ "java","-jar", "todo.jar" ]
