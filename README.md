# Java on App Service
Demo of running Java applications on App Service.

## Getting Started

The simplest steps are the following (no IDE required):

* Get the project source code.
* Ensure you are running Java SE 11 or Java SE 17.
* Make sure JAVA_HOME is set.
* As long as you have Maven set up properly, navigate to the project source `jakartaee` and type:
```
mvn clean package cargo:run
```
* Go to http://localhost:8080/todo.jsp

# To Do
* Get it working on React, Jakarta EE 10/JBoss EAP 8 and PostgreSQL.
* Get it working on latest Spring Boot and Hibernate.
* Get it working on Tomcat using latest Spring Framework and Hibernate.
