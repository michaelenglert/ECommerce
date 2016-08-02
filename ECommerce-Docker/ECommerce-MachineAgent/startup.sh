#!/bin/bash

#########
# Configure analytics to report to Event Service
########

ANALYTICS_PROPERTIES_FILE=/appdynamics/agents/machine-agent/monitors/analytics-agent/conf/analytics-agent.properties
MONITOR_XML=/appdynamics/agents/machine-agent/monitors/analytics-agent/monitor.xml

echo "Setting Account Name : ${APPDYNAMICS_ANALYTICS_ACCOUNT_NAME}" >> logs/startup.log
sed -i "/^http.event.accountName=/c\http.event.accountName=${APPDYNAMICS_ANALYTICS_ACCOUNT_NAME}" ${ANALYTICS_PROPERTIES_FILE}

echo "Setting Account Access Key : ${APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY}" >> logs/startup.log
sed -i "/^http.event.accessKey=/c\http.event.accessKey=${APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY}" ${ANALYTICS_PROPERTIES_FILE}

echo "Setting End Point : ${APPDYNAMICS_ANALYTICS_ENDPOINT}" >> logs/startup.log
sed -i "/^http.event.endpoint=/c\http.event.endpoint=${APPDYNAMICS_ANALYTICS_ENDPOINT}" ${ANALYTICS_PROPERTIES_FILE}

echo "Enabling Analytics in monitor.xml" >> logs/startup.log
sed -i 's#<enabled>false</enabled>#<enabled>true</enabled>#g' ${MONITOR_XML}

##########
# Start machine agent
#########

nohup java -jar /appdynamics/agents/machine-agent/machineagent.jar > /appdynamics/agents/machine-agent/machine-agent.out 2>&1
