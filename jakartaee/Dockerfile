FROM quay.io/wildfly/wildfly:28.0.0.Final-jdk17

# Set environment variables
ENV JBOSS_HOME=/opt/jboss/wildfly
ENV POSTGRESQL_JNDI_NAME=java:/jdbc/TodoAppDatabase
ENV POSTGRESQL_DB_URL=jdbc:postgresql://localhost:5432/todoapp_db
ENV POSTGRESQL_DB_USER=postgres
ENV POSTGRESQL_DRIVER_DIR=$JBOSS_HOME/modules/system/layers/base/org/postgresql/main

# Add a management user
RUN ${JBOSS_HOME}/bin/add-user.sh admin admin#7rules --silent

# Add war file to the deployments directory
ADD target/todo.war ${JBOSS_HOME}/standalone/deployments/

# Add PostgreSQL JDBC driver
RUN mkdir -p ${POSTGRESQL_DRIVER_DIR}
ADD src/main/wildfly/module.xml ${POSTGRESQL_DRIVER_DIR}
ADD target/dependencies/postgresql-42.7.3.jar ${POSTGRESQL_DRIVER_DIR}/postgresql.jar

# Copy the WildFly initialization script
COPY src/main/wildfly/configure-wildfly.cli /opt/jboss/configure-wildfly.cli

# Run CLI script to configure data source
RUN $JBOSS_HOME/bin/jboss-cli.sh --file=/opt/jboss/configure-wildfly.cli

# Clean up the standalone_xml_history/current directory
RUN rm -rf ${JBOSS_HOME}/standalone/configuration/standalone_xml_history/current/*

# Expose WildFly management and application ports
EXPOSE 8080 9990
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
