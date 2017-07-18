#!/bin/bash

if [ -n "${create_schema}" ]; then
	export CREATE_SCHEMA=false;
fi

if [ -z "JVM_ROUTE" ]; then
	export JVM_ROUTE="route1";
fi

export JAVA_OPTS="-Xmx512m -XX:MaxPermSize=256m"
export JMX_OPTS="-Dcom.sun.management.jmxremote.port=8888  -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

export APP_AGENT_JAVA_OPTS="${JAVA_OPTS} -DjvmRoute=${JVM_ROUTE} -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager";
