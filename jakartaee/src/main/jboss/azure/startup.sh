# This file is executed when your application is started.

# The Service Connector has loaded JARs to /usr/local/appservice/jdbc/postgresql for PostgreSQL database 
# connection using Managed Identity. Ensure all the JARs are properly added to the classpath.

POSTGRESQL_RELATED_JARS=":"
for f in /usr/local/appservice/jdbc/postgresql/*.jar; do
    POSTGRESQL_RELATED_JARS="$f:$POSTGRESQL_RELATED_JARS"
done
export POSTGRESQL_RELATED_JARS=${POSTGRESQL_RELATED_JARS}

# Configure the data source
$JBOSS_HOME/bin/jboss-cli.sh --connect --file=/home/site/scripts/configure-data-source.cli --resolve-parameter-values