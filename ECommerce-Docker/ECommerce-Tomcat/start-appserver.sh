#!/bin/sh
source /env.sh

echo JMX_OPTS: ${JMX_OPTS}
cd ${CATALINA_HOME}/bin;
java ${APP_AGENT_JAVA_OPTS} ${JMX_OPTS} -cp ${CATALINA_HOME}/bin/bootstrap.jar:${CATALINA_HOME}/bin/tomcat-juli.jar org.apache.catalina.startup.Bootstrap > appserver-agent-startup.out 2>&1
