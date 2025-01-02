## Getting Started

The simplest steps are the following (no IDE required):

* Get the project source code.
* Ensure you are running Java SE 17.
* Make sure JAVA_HOME is set.
* Docker.

Navigate to the project source `tomcat` and build the application:

```
mvn clean package
```

Start Spring Boot application and the PostgreSQL database using Docker Compose:

```bash
# docker-compose down -v

docker-compose up --build
```

By following these steps, you should be able to run the application and PostgreSQL database using Docker Compose. The application will be accessible at http://localhost:8080/.

Rest API:

```bash
curl -v -X POST http://localhost:8080/resources/todo -H "Content-Type: application/json" -d '
{
"description": "Test REST",
"completed": "true"
}'

curl http://localhost:8080/resources/todo
```