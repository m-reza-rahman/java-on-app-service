# Deploying Java Applications to Azure App Service
This demo shows how you can deploy Spring Boot, Spring Framework, Hibernate, Tomcat, Java EE, Jakarta EE, and JBoss EAP applications to App Service (App Service is the premier PaaS platform on Azure). It is the demo for [this](https://sessionize.com/s/reza-rahman/spring-quarkus-tomcat-jakarta-ee-hyperscale-paas-o/122890) talk.

We use Azure PostgreSQL but you can use Azure SQL, Azure MySQL, or Oracle DB@Azure.

## Setup
* Install JDK 17 (we used [Eclipse Temurin OpenJDK 17 LTS](https://adoptium.net/?variant=openjdk17)). Make sure JAVA_HOME is set.
* Install [Maven](https://maven.apache.org/download.cgi).
* Download this repository somewhere in your file system (easiest way might be to download as a zip and extract).
* If you wish to run locally before deploying to Azure, install [Docker](https://docs.docker.com/get-started/get-docker/).
* You will need an Azure subscription. If you don't have one, you can get one for free for one year [here](https://azure.microsoft.com/en-us/free).

## Running the Applications
Each of the parts of this repository can be run independently, which means you can go directly to the parts that interest you most. The following is just one logical sequence.

* Deploying a Spring Boot application using Java SE on App Service. The [spring-boot](/spring-boot) folder shows how this is done.
* Deploying a Jakarta EE application using JBoss EAP on App Service. The [jakartaee](/jakartaee) folder shows how this is done.
* Deploying a Spring Framework application using Tomcat on App Service. The [tomcat](/tomcat) folder shows how this is done.

## To Do
* Get it working on latest Spring Boot and Hibernate.
* Get it working on Tomcat using latest Spring Framework and Hibernate.
