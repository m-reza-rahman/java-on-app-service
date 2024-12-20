## Getting Started

The simplest steps are the following (no IDE required):

* Get the project source code.
* Ensure you are running Java SE 17.
* Make sure JAVA_HOME is set.

Start PostgreSQL database in a container with:

```bash
docker run --rm --name todoapp \
  -p 5432:5432 \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_DB=todoapp_db \
  docker.io/library/postgres
```

Navigate to the project source `jakartaee` and build the application:

```
mvn clean package
```

Create the required environment variables used by WildFly to connect to the PostgreSQL database and start the server:

```bash
export POSTGRESQL_USER=postgres
export POSTGRESQL_PASSWORD=admin
export POSTGRESQL_DATABASE=todoapp_db

./target/server/bin/standalone.sh
```

Go to http://localhost:8080/

Rest API:

```bash
curl -v -X POST http://localhost:8080/resources/todo/Galia -H "Content-Type: application/json" -d '
{
"description": "Test REST",
"completed": "true"
}'

curl http://localhost:8080/resources/todo/Galia
```

