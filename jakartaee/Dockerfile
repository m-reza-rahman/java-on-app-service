FROM quay.io/wildfly/wildfly:28.0.0.Final-jdk17
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin#7rules --silent

ADD target/todo.war /opt/jboss/wildfly/standalone/deployments/
# Set environment variables
ENV POSTGRESQL_JDBC_VERSION=42.7.3
ENV POSTGRESQL_JDBC_JAR=postgresql-${POSTGRESQL_JDBC_VERSION}.jar
ENV POSTGRESQL_JNDI_NAME=java:/jdbc/TodoAppDatabase
ENV POSTGRESQL_DB_URL=jdbc:postgresql://localhost:5432/todoapp_db
ENV POSTGRESQL_DB_USER=postgres
ENV JBOSS_HOME=/opt/jboss/wildfly
ENV POSTGRESQL_JDBC_JAR_PATH=$JBOSS_HOME/modules/system/layers/base/org/postgresql/main/$POSTGRESQL_JDBC_JAR

# Add PostgreSQL JDBC driver
RUN mkdir -p $JBOSS_HOME/modules/system/layers/base/org/postgresql/main
RUN curl -L -o ${POSTGRESQL_JDBC_JAR_PATH} https://jdbc.postgresql.org/download/$POSTGRESQL_JDBC_JAR

# Create the module.xml for PostgreSQL driver
RUN echo '<module xmlns="urn:jboss:module:1.3" name="org.postgresql">' > $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml \
    && echo '    <resources>' >> $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml \
    && echo '        <resource-root path="'${POSTGRESQL_JDBC_JAR_PATH}'"/>' >> $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml \
    && echo '    </resources>' >> $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml \
    && echo '    <dependencies>' >> $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml \
    && echo '        <module name="javax.api"/>' >> $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml \
    && echo '        <module name="javax.transaction.api"/>' >> $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml \
    && echo '    </dependencies>' >> $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml \
    && echo '</module>' >> $JBOSS_HOME/modules/system/layers/base/org/postgresql/main/module.xml

# Copy the WildFly initialization script
COPY configure-wildfly.cli /opt/jboss/configure-wildfly.cli

# Run CLI script to configure data source
RUN $JBOSS_HOME/bin/jboss-cli.sh --file=/opt/jboss/configure-wildfly.cli

# Clean up the standalone_xml_history/current directory
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current/*

# Expose WildFly management and application ports
EXPOSE 8080 9990
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
