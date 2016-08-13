#!/bin/bash
VERSION_AGENT=4.2.0.2-16.08.02
APP_SERVER_AGENT=/Users/rbolton/Documents/Code/appd/agents/AppServerAgent-4.2.5.0.zip
LOCAL_TOMCAT=/Users/rbolton/Documents/Code/appd/agents/apache-tomcat-7.0.70.tar.gz
CONTR_HOST=dev.demo.appdynamics.com
CONTR_PORT=8090
APP_NAME=ECommerce-Rob
VERSION_BASE=16.08.02
ACCOUNT_NAME=customer1_f16aea9b-844d-476d-92db-60f3acaa620d
ACCESS_KEY=4dea0c08-003b-4732-b07d-7abec9c098ba
EVENT_ENDPOINT=https://analytics.api.appdynamics.com
UNIQUE_HOST_ID=bolton
SHARED_LOGS=/Users/rbolton/Documents/Code/appd/sharedAppDLogs
AWS_ACCESS_KEY=
AWS_SECRET_KEY=

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

docker stop web
docker stop web1
docker stop ws
docker rm web
docker rm web1
docker rm ws
echo "Docker web containers stopped"

echo -n "ws: "; docker run --name ws -p 8080:8080 -h ${APP_NAME}-ws -e create_schema=true -e ws=true \
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

echo -n "web1: "; docker run --name web1 -h ${APP_NAME}-web1 -v ${SHARED_LOGS}/web2:/tomcat/logs -e JVM_ROUTE=route2 -e web=true \
    -e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
    -e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
    -e NODE_NAME=${APP_NAME}_WEB2_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=ECommerce-Services \
    -e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} -e APPDYNAMICS_AGENT_UNIQUE_HOST_ID=${UNIQUE_HOST_ID} \
    --link db:db --link oracle-db:oracle-db --link ws:ws --link jms:jms --link machine-agent:machine-agent -d appddemo/ecommerce-tomcat:$VERSION_AGENT
