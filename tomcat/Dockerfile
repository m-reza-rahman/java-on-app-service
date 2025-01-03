FROM tomcat:10.1-jdk17

ENV CATALINA_HOME=/usr/local/tomcat
ENV DEPLOY_DIR=$CATALINA_HOME/webapps

COPY target/ROOT.war $DEPLOY_DIR/

EXPOSE 8080
CMD ["catalina.sh", "run"]
