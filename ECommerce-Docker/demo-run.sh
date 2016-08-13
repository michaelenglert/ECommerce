#!/bin/bash

# VARIABLES
CONTR_HOST=
CONTR_PORT=
APP_NAME=
VERSION_AGENT=
VERSION_BASE=
ACCOUNT_NAME=
ACCESS_KEY=
EVENT_ENDPOINT=
UNIQUE_HOST_ID=
SHARED_LOGS=
AWS_ACCESS_KEY=
AWS_SECRET_KEY=

echo -n "uninstrumented"; docker run --name uninstrumented -d -p 3000:3000 appddemo/ecommerce-uninstrumented:$VERSION_BASE
echo -n "oracle-db: "; docker run --name oracle-db -d -p 1521:1521 -p 2222:22 appddemo/ecommerce-oracle:${VERSION_BASE}
echo -n "db: "; docker run --name db -e MYSQL_ROOT_PASSWORD=singcontroller -p 3306:3306 -p 2223:22 -d appddemo/ecommerce-mysql:${VERSION_BASE}
echo -n "jms: "; docker run --name jms -d appddemo/ecommerce-activemq:${VERSION_BASE}
echo -n "analytics: "; docker run -d -h ecommerce-analytics --name machine-agent -P -v ${SHARED_LOGS}:/appdynamics/shared-logs -v `pwd`/analytics-jobs:/appdynamics/agents/machine-agent/monitors/analytics-agent/conf/job -e APPDYNAMICS_ANALYTICS_ACCOUNT_NAME=${ACCOUNT_NAME} -e APPDYNAMICS_ANALYTICS_ENDPOINT=${EVENT_ENDPOINT} -e APPDYNAMICS_CONTROLLER_HOST_NAME=${CONTR_HOST} -e APPDYNAMICS_CONTROLLER_PORT=${CONTR_PORT} -e APPDYNAMICS_AGENT_ACCOUNT_NAME=customer1 -e APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY=${ACCESS_KEY} appddemo/machineagent-analytics:${VERSION_AGENT}
sleep 30

echo -n "ws: "; docker run --name ws -h ${APP_NAME}-ws -e create_schema=true -e ws=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=${APP_NAME}_WS_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=Inventory-Services \
	-e APPDYNAMICS_AGENT_UNIQUE_HOST_ID=${UNIQUE_HOST_ID} --link db:db \
	--link jms:jms --link oracle-db:oracle-db --link uninstrumented:api.mainsupplier.com --link uninstrumented:api.secondarysupplier.com -d appddemo/ecommerce-tomcat:$VERSION_AGENT

echo -n "web: "; docker run --name web -h ${APP_NAME}-web -v ${SHARED_LOGS}/web1:/tomcat/logs -e JVM_ROUTE=route1 -e web=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=${APP_NAME}_WEB1_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=ECommerce-Services \
	-e APPDYNAMICS_AGENT_UNIQUE_HOST_ID=${UNIQUE_HOST_ID} \
	--link db:db --link oracle-db:oracle-db --link ws:ws --link jms:jms --link machine-agent:machine-agent -d appddemo/ecommerce-tomcat:$VERSION_AGENT

sleep 30

echo -n "fulfillment: "; docker run --name fulfillment -h ${APP_NAME}-fulfillment -e web=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=Fulfillment -e APP_NAME=${APP_NAME}-Fulfillment -e TIER_NAME=Fulfillment-Services \
	-e AWS_ACCESS_KEY=${AWS_ACCESS_KEY} -e AWS_SECRET_KEY=${AWS_SECRET_KEY} \
	-e APPDYNAMICS_AGENT_UNIQUE_HOST_ID=${UNIQUE_HOST_ID} \
	--link db:db --link ws:ws --link jms:jms --link oracle-db:oracle-db -d appddemo/ecommerce-tomcat:$VERSION_AGENT

sleep 30

echo -n "web1: "; docker run --name web1 -h ${APP_NAME}-web1 -v ${SHARED_LOGS}/web2:/tomcat/logs -e JVM_ROUTE=route2 -e web=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=${APP_NAME}_WEB2_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=ECommerce-Services \
	-e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} -e APPDYNAMICS_AGENT_UNIQUE_HOST_ID=${UNIQUE_HOST_ID} \
	--link db:db --link oracle-db:oracle-db --link ws:ws --link jms:jms --link machine-agent:machine-agent -d appddemo/ecommerce-tomcat:$VERSION_AGENT

sleep 30

echo -n "lbr: "; docker run --name=lbr -h ${APP_NAME}-lbr \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e APP_NAME=${APP_NAME} -e TIER_NAME=Web-Tier-Services -e NODE_NAME=${APP_NAME}-Apache \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e APPDYNAMICS_AGENT_UNIQUE_HOST_ID=${UNIQUE_HOST_ID} \
	--link web:web --link web1:web1 -p 80:80 -d appddemo/ecommerce-lbr:$VERSION_AGENT

echo -n "msg: "; docker run --name msg -h ${APP_NAME}-msg -e jms=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=${APP_NAME}_JMS_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=Order-Processing-Services \
	-e APPDYNAMICS_AGENT_UNIQUE_HOST_ID=${UNIQUE_HOST_ID} \
	--link db:db --link jms:jms --link oracle-db:oracle-db --link fulfillment:fulfillment -d appddemo/ecommerce-tomcat:$VERSION_AGENT

exit 0

