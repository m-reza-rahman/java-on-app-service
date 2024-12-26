# Use the official Tomcat base image
FROM tomcat:10.1-jdk17

# Set environment variables
ENV CATALINA_HOME=/usr/local/tomcat
ENV DEPLOY_DIR=$CATALINA_HOME/webapps

# Copy the WAR file to the Tomcat webapps directory
COPY target/ROOT.war $DEPLOY_DIR/

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
