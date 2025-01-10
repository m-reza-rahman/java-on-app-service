FROM quay.io/wildfly/wildfly:28.0.0.Final-jdk17

# Set environment variables
ENV JBOSS_HOME=/opt/jboss/wildfly

# Add a management user
RUN ${JBOSS_HOME}/bin/add-user.sh admin admin#7rules --silent

# Add war file to the deployments directory
ADD target/todo.war ${JBOSS_HOME}/standalone/deployments/

# Add PostgreSQL JDBC driver files
ADD src/main/jboss/postgresql-module.xml /tmp
ADD target/dependencies/postgresql.jar /tmp

# Run CLI script to configure data source
COPY src/main/jboss/configure-data-source.cli /opt/jboss/configure-data-source.cli
RUN $JBOSS_HOME/bin/jboss-cli.sh --file=/opt/jboss/configure-data-source.cli

# Clean up the standalone_xml_history/current directory
RUN rm -rf ${JBOSS_HOME}/standalone/configuration/standalone_xml_history/current/*

# Expose WildFly management and application ports
EXPOSE 8080 9990
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
