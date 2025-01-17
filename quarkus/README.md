# Deploying a Quarkus Application to Java SE on Azure App Service
This demo shows how you can deploy a Quarkus application to Azure using 
Java SE on App Service.

## Run Locally (Optional)

You can run the application using Quarkus development mode.

Navigate to the project source `quarkus` and run the application with Quarkus 
development mode:

```bash
mvn quarkus:dev
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

## Set up Java SE on App Service
* Go to the [Azure portal](http://portal.azure.com).
* Select 'Create a resource'. In the search box, enter and select 'Web App'. 
Hit create.
* Select todo-app-group-`<your suffix>` as the resource group and enter 
todo-quarkus-app as application name. Choose Java 17 as your 
runtime stack and Java SE as the Java web server stack. You can optionally pick the 
free tier for your pricing plan.
* Click next until you reach the monitoring tab. If you want faster deployment, 
turn off Application Insights. You should definitely do 
this for the free tier where compute capacity is very limited.
* Finish creating the resource.

## Connect PostgreSQL Using Service Connector
* In the portal home, go to 'All resources'. Find and click on the App Service 
instance named todo-quarkus-app. Open the Settings -> Service Connector panel.
* Select Create. Choose 'DB for PostgreSQL flexible server' as your service type. 
Select your PostgreSQL flexible server todo-db-`<your suffix>`. Select 'postgres' as 
your PostgreSQL database. Select 'Java' as your Cient type.
* Click next. Select 'System assigned managed identity' for Authenication.
* Click next until you find Review + Create.
* Follow the instructions to finish creating the resource.

Note down the database user created by the Service Connector, you can find it from 
the log as the following output shows. In this example, the database user is 
`aad_postgresql_e2220`.

```
Enabling WebApp System Identity...
Connecting to database...
Running query: select * from pgaadauth_create_principal_with_oid('aad_postgresql_e2220', '8cf396b8-b4b1-4c94-a3fd-2d446829ada8', 'service', false, false);
Running query: GRANT ALL PRIVILEGES ON DATABASE "postgres" TO "aad_postgresql_e2220";
Running query: GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO "aad_postgresql_e2220";
Running query: GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO "aad_postgresql_e2220";
Running query: GRANT CREATE ON SCHEMA public TO "aad_postgresql_e2220";
```

## Set up Environment Variables
* Open the Settings -> Environment variables panel.
* Add the following variables: 

| Variable Name | Value |
|---------------|-------|
| `quarkus.datasource.jdbc.url` | jdbc:postgresql://todo-db-`<your suffix>`.postgres.database.azure.com:5432/postgres?authenticationPluginClassName=com.azure.identity.extensions.jdbc.postgresql.AzurePostgresqlAuthenticationPlugin&sslmode=require |
| `quarkus.datasource.username` | `<user-created-by-service-connector>` |

## Clean the Database
This application will drop and recreate the table `todoitem` and the sequence 
`todoitem_seq` in the PostgreSQL database, using the managed identity user created by 
the Service Connector. You need to ensure that the PostgreSQL database does not contain an 
existing schema, as the application will fail to deploy if the managed identity user 
is not the owner of the existing schema.

* Open a [Cloud Shell](https://learn.microsoft.com/azure/cloud-shell/overview) from the Azure Portal.
* Connect to the database:
  ```
  psql "host=todo-db-`<your suffix>`.postgres.database.azure.com port=5432 dbname=postgres user=postgres password=`<your password>`"
  ```
* Drop the existing resources:
  ```
  drop table if exists ToDoItem cascade;
  drop sequence if exists ToDoItem_SEQ;
  ```

## Start the Application on Java SE on App Service
* Open a console and execute the following to log onto Azure.

	```
	az login
	```

* Open the [pom.xml](pom.xml) file and replace occurrences of `reza` 
with `<your suffix>`.
* You should note the pom.xml. In particular, we have included the configuration for 
the Azure Maven plugin we are going to use to deploy 
the application to Java SE on App Service:

    ```xml
    <plugin>
        <groupId>com.microsoft.azure</groupId>
        <artifactId>azure-webapp-maven-plugin</artifactId>
        <version>2.13.0</version>
        <configuration>
            <appName>todo-quarkus-app</appName>
            <resourceGroup>todo-app-group-reza</resourceGroup>
            <javaVersion>Java 17</javaVersion>
            <webContainer>Java SE</webContainer>
            <appSettings>
                <property>
                    <name>PORT</name>
                    <value>8080</value>
                </property>
                <property>
                    <name>WEBSITES_PORT</name>
                    <value>8080</value>
                </property>
                <property>
                    <name>WEBSITE_SKIP_AUTOCONFIGURE_DATABASE</name>
                    <value>true</value>
                </property>
            </appSettings>
            <deployment>
                <resources>
                    <resource>
                        <directory>${project.basedir}/target</directory>
                        <includes>
                            <include>todo-runner.jar</include>
                        </includes>
                    </resource>
                </resources>
            </deployment>
        </configuration>
    </plugin>
    ```

* Use Maven to deploy the application from the `quarkus` directory:

  ```
  mvn clean package azure-webapp:deploy
  ```

* Keep an eye on the console output. You will see the application deployment progress. 
It may take a while for the deployment to complete.
* Once successfully deployed, you can access the application through its public 
endpoint. To get the public endpoint, go to 
portal home -> 'All resources'. Find and click on the App Service instance named 
todo-quarkus-app. Go to the overview panel and copy the 
default domain. The application will be available at a URL 
like: https://todo-quarkus-app-suffix.azurewebsites.net.
* Once the application starts, you can test the REST service at the 
URL: https://todo-quarkus-app-suffix.azurewebsites.net/resources/todo or via 
the React UI at https://todo-quarkus-app-suffix.azurewebsites.net.
