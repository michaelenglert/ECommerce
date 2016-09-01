#!/bin/bash


VERSION_AGENT=4.2.5.0-16.08.12
APP_SERVER_AGENT=/Users/gabriella.querales/Documents/AppDyn/Code/appd/agents/AppServerAgent-4.2.4.1.zip
LOCAL_TOMCAT=/Users/gabriella.querales/Documents/AppDyn/Code/appd/agents/apache-tomcat-7.0.70.tar.gz
CONTR_HOST=dev.demo.appdynamics.com
CONTR_PORT=8090
APP_NAME=ECommerce-GQ
VERSION_BASE=16.08.02
ACCOUNT_NAME=customer1_f16aea9b-844d-476d-92db-60f3acaa620d
ACCESS_KEY=4dea0c08-003b-4732-b07d-7abec9c098ba
EVENT_ENDPOINT=https://analytics.api.appdynamics.com
UNIQUE_HOST_ID=gabriella.querales
SHARED_LOGS=/Users/gabriella.querales/Documents/AppDyn/Code/appd/agents/sharedAppDLogs
AWS_ACCESS_KEY=
AWS_SECRET_KEY=

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
echo "Docker web containers stopped"

sleep 30





echo -n "oracle-db: "; docker run --name oracle-db -d -p 1521:1521 -p 2222:22 appddemo/ecommerce-oracle:${VERSION_BASE}
echo -n "db: "; docker run --name db -e MYSQL_ROOT_PASSWORD=singcontroller -p 3306:3306 -p 2223:22 -d appddemo/ecommerce-mysql:${VERSION_BASE}
echo -n "jms: "; docker run --name jms -d appddemo/ecommerce-activemq:${VERSION_BASE}
echo -n "analytics: "; docker run -d -h ecommerce-analytics --name machine-agent -P -v ${SHARED_LOGS}:/appdynamics/shared-logs -v `pwd`/analytics-jobs:/appdynamics/agents/machine-agent/monitors/analytics-agent/conf/job -e APPDYNAMICS_ANALYTICS_ACCOUNT_NAME=${ACCOUNT_NAME} -e APPDYNAMICS_ANALYTICS_ENDPOINT=${EVENT_ENDPOINT} -e APPDYNAMICS_CONTROLLER_HOST_NAME=${CONTR_HOST} -e APPDYNAMICS_CONTROLLER_PORT=${CONTR_PORT} -e APPDYNAMICS_AGENT_ACCOUNT_NAME=customer1 -e APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY=${ACCESS_KEY} appddemo/machineagent-analytics:${VERSION_AGENT}
sleep 30


#copy agent and tomcat
(cp ${APP_SERVER_AGENT} ECommerce-Tomcat/AppServerAgent.zip)
(cp ${LOCAL_TOMCAT} ECommerce-Tomcat/apache-tomcat.tar.gz)
echo "Copied Agent and Tomcat for ECommerce-Tomcat"

#copy and build
(cd ../ECommerce-Java/ && gradle war uberjar)
(cd ../)

(cp ../ECommerce-Java/ECommerce-Web/build/libs/appdynamicspilot.war ECommerce-Tomcat/)
(cp ../ECommerce-Java/ECommerce-JMS/build/libs/appdynamicspilotjms.war ECommerce-Tomcat/)
(cp ../ECommerce-Java/ECommerce-WS/build/libs/cart.war ECommerce-Tomcat/)
(cp ../ECommerce-Java/ECommerce-Web/libs/ojdbc6.jar ECommerce-Tomcat/)
(cp ../ECommerce-Java/build.gradle ECommerce-Tomcat/)
(cp ../ECommerce-Java/settings.gradle ECommerce-Tomcat/)
(cp ../ECommerce-Java/database.properties ECommerce-Tomcat/)
(cp ../ECommerce-Java/schema.sql ECommerce-Tomcat/)
(cp ../ECommerce-Java/oracle.sql ECommerce-Tomcat/)
(cp ../ECommerce-Java/zips.sql ECommerce-Tomcat/)
echo "Files copied over"

cd ECommerce-Tomcat && docker build --no-cache -t appddemo/ecommerce-tomcat:$VERSION_AGENT .

sleep 30



echo -n "ws: "; docker run --name ws -h ${APP_NAME}-ws -e create_schema=true -e ws=true \
    -e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
    -e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
    -e NODE_NAME=${APP_NAME}_WS_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=Inventory-Services \
    -e APPDYNAMICS_AGENT_UNIQUE_HOST_ID=${UNIQUE_HOST_ID} --link db:db \
    --link jms:jms --link oracle-db:oracle-db -d appddemo/ecommerce-tomcat:$VERSION_AGENT

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

docker ps

