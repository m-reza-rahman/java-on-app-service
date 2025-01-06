FROM tomcat:10.1-jdk17

ENV CATALINA_HOME=/usr/local/tomcat
ENV DEPLOY_DIR=$CATALINA_HOME/webapps

COPY target/todo.war $DEPLOY_DIR/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
