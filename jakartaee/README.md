# Deploying a Java/Jakarta EE Application to JBoss EAP on Azure App Service
This demo shows how you can deploy a Java/Jakarta EE application to Azure using JBoss EAP on App Service.

## Build the Project
Navigate to the project source `jakartaee` and build the application:

```
mvn clean package
```

## Run Locally (Optional)
Start the application and PostgreSQL database using Docker Compose:

```
# docker-compose down -v
docker-compose up --build
```

Once the application starts, it will be accessible at http://localhost:8080.

You can explore the REST API for the application:

```bash
curl -v -X POST http://localhost:8080/resources/todo -H "Content-Type: application/json" -d '
{
"description": "Test REST API",
"completed": "true"
}'

curl http://localhost:8080/resources/todo
```