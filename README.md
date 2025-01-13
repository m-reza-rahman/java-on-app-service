# Deploying Java Applications to Azure App Service
This demo shows how you can deploy Spring Boot, Spring Framework, Hibernate, Tomcat, 
Java EE, Jakarta EE, and JBoss EAP applications to App Service (App Service is the 
premier PaaS platform on Azure). It is the demo for 
[this](https://sessionize.com/s/reza-rahman/spring-quarkus-tomcat-jakarta-ee-hyperscale-paas-o/122890) 
talk.

We use Azure PostgreSQL but you can use Azure SQL, Azure MySQL, or Oracle DB@Azure. 
React is used as the JavaScript front-end.

## Setup
* Install JDK 17 (we used 
[Eclipse Temurin OpenJDK 17 LTS](https://adoptium.net/?variant=openjdk17)). 
Make sure JAVA_HOME is set.
* Set up [Maven](https://maven.apache.org/download.cgi).
* Download this repository somewhere in your file system (easiest way might be to 
download as a zip and extract).
* If you wish to run locally before deploying to Azure, 
install [Docker](https://docs.docker.com/get-started/get-docker/).
* You will need an Azure subscription. If you don't have one, you can get one for 
free for one year [here](https://azure.microsoft.com/en-us/free).
* Install the 
[Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli?view=azure-cli-latest).

## Start Managed PostgreSQL on Azure
* Go to the [Azure portal](http://portal.azure.com).
* Select 'Create a resource'. In the search box, enter and select 
'Azure Database for PostgreSQL Flexible Server'. Hit create.
* Create a new resource group named todo-app-group-`<your suffix>` 
(the suffix could be your first name such as "reza").
Specify the Server name to be todo-db-`<your suffix>` (the suffix could be your 
first name such as "reza").
* Choose Microsoft Entra authentication. Set the Microsoft Entra admin 
to your working Azure user.
* Click Next to go to the Networking tab.
* Enable access to Azure services.
* Create the resource. It will take a moment for the database to deploy and be ready 
for use.
* In the portal home, go to 'All resources'. Find and click on 
todo-db-`<your suffix>`. Open the Settings -> Server parameters panel. Set the 
'require_secure_transport' parameter to 'OFF', and then hit 'Save'.

## Running the Applications
Each of the parts of this repository can be run independently, which means you can go 
directly to the parts that interest you most. The following is just one logical 
sequence.

* Deploying a Spring Boot application using Java SE on App Service. 
The [spring-boot](/spring-boot) folder shows how this is done.
* Deploying a Jakarta EE application using JBoss EAP on App Service. 
The [jakartaee](/jakartaee) folder shows how this is done.
* Deploying a Spring Framework application using Tomcat on App Service. 
The [tomcat](/tomcat) folder shows how this is done.

## Clean up
Once you are done exploring the demo, you should delete the 
todo-app-group-`<your suffix>` resource group. You can do this by going to 
the portal, going to resource groups, finding and clicking on 
todo-app-group-`<your suffix>` and hitting delete. This is especially 
important if you are not using a free subscription.
