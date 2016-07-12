# This script is provided for illustration purposes only.
#
# To build the ECommerce demo application, you will need to download the following components:
# 1. An appropriate version of the Oracle Java 7 JDK
#    (http://www.oracle.com/technetwork/java/javase/downloads/index.html)
# 2. Correct versions for the AppDynamics AppServer Agent, Machine Agent and Database Monitoring Agent for your Controller installation
#    (https://download.appdynamics.com)
#
# To run the ECommerce demo application, you will also need to:
# 1. Build and run the ECommerce-Oracle docker container
#    The Dockerfile is available here (https://github.com/Appdynamics/ECommerce-Docker/tree/master/ECommerce-Oracle)
#    The container requires you to downlaod an appropriate version of the Oracle Database Express Edition 11g Release 2
#    (http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html)
# 2. Download and run the official Docker mysql container
#    (https://registry.hub.docker.com/_/mysql/)

#!/bin/sh

# Controller host/port
CONTR_HOST=
CONTR_PORT=
APP_NAME=ECommerce
VERSION=

# EUM config parameters
EUM_ENDPOINT=
RUM_KEY=

# Analytics config parameters
ACCOUNT_NAME=
ACCESS_KEY=
EVENT_ENDPOINT=

# SIM Hierarchy parameters
# Uncomment to use AWS metadata
SIM_HIERACRHY_1=$(curl -s http://169.254.169.254/latest/meta-data/placement/availability-zone)
SIM_HIERARCHY_2=$(curl -s http://169.254.169.254//latest/meta-data/public-hostname)

# Load gen parameters
NUM_OF_USERS=
RAMP_TIME=
TIME_BETWEEN_RUNS=
WAIT_TIME=

# AWS Credentials
AWS_ACCESS_KEY=
AWS_SECRET_KEY=

docker pull appdynamics/ecommerce-activemq
docker pull appdynamics/ecommerce-oracle
docker pull appdynamics/ecommerce-mysql
docker pull appdynamics/ecommerce-tomcat:${VERSION}
docker pull appdynamics/ecommerce-dbagent:${VERSION}
docker pull appdynamics/ecommerce-load
docker pull appdynamics/ecommerce-lbr:4202
docker pull appdynamics/ecommerce-fulfillment-client:${VERSION}
docker pull appdynamics/ecommerce-synapse:${VERSION}
docker pull appdynamics/ecommerce-angular:${VERSION}

#source env.sh

echo -n "oracle-db: "; docker run --name oracle-db -d -p 1521:1521 -p 2222:22 appdynamics/ecommerce-oracle
echo -n "db: "; docker run --name db -e MYSQL_ROOT_PASSWORD=singcontroller -p 3306:3306 -p 2223:22 -d appdynamics/ecommerce-mysql
echo -n "jms: "; docker run --name jms -d appdynamics/ecommerce-activemq

sleep 30

echo -n "ws: "; docker run --name ws -h ${APP_NAME}-ws -e create_schema=true -e ws=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=${APP_NAME}_WS_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=Inventory-Services \
	-e SIM_HIERARCHY_1=${SIM_HIERARCHY_1} -e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} --link db:db \
	--link jms:jms --link oracle-db:oracle-db -d appdynamics/ecommerce-tomcat:$VERSION

echo -n "web: "; docker run --name web -h ${APP_NAME}-web -e JVM_ROUTE=route1 -e web=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=${APP_NAME}_WEB1_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=ECommerce-Services \
	-e SIM_HIERARCHY_1=${SIM_HIERARCHY_1} -e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} \
	--link db:db --link oracle-db:oracle-db --link ws:ws --link jms:jms -d appdynamics/ecommerce-tomcat:$VERSION

sleep 30

echo -n "fulfillment: "; docker run --name fulfillment -h ${APP_NAME}-fulfillment -e web=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=Fulfillment -e APP_NAME=${APP_NAME}-Fulfillment -e TIER_NAME=Fulfillment-Services \
	-e AWS_ACCESS_KEY=${AWS_ACCESS_KEY} -e AWS_SECRET_KEY=${AWS_SECRET_KEY} \
	-e SIM_HIERARCHY_1=${SIM_HIERARCHY_1} -e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} \
	--link db:db --link ws:ws --link jms:jms --link oracle-db:oracle-db -d appdynamics/ecommerce-tomcat:$VERSION

sleep 30

echo -n "fulfillment-client: "; docker run --name fulfillment-client -h ${APP_NAME}-fulfillment-client \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} \
        -e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=FulfillmentClient1 -e APP_NAME=${APP_NAME}-Fulfillment -e TIER_NAME=Fulfillment-Client-Services \
	-e AWS_ACCESS_KEY=${AWS_ACCESS_KEY} -e AWS_SECRET_KEY=${AWS_SECRET_KEY} \
	-e SIM_HIERARCHY_1=${SIM_HIERARCHY_1} -e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} \
	-d appdynamics/ecommerce-fulfillment-client:$VERSION

sleep 30

echo -n "web1: "; docker run --name web1 -h ${APP_NAME}-web1 -e JVM_ROUTE=route2 -e web=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=${APP_NAME}_WEB2_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=ECommerce-Services \
	-e SIM_HIERARCHY_1=${SIM_HIERARCHY_1} -e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} \
	--link db:db --link oracle-db:oracle-db --link ws:ws --link jms:jms -d appdynamics/ecommerce-tomcat:$VERSION

sleep 30

echo -n "lbr: "; docker run --name=lbr -h ${APP_NAME}-lbr \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e APP_NAME=${APP_NAME} -e TIER_NAME=Web-Tier-Services -e NODE_NAME=${APP_NAME}-Apache \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e SIM_HIERARCHY_1=${SIM_HIERARCHY_1} -e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} \
	--link web:web --link web1:web1 -p 80:80 -d appdynamics/ecommerce-lbr:4202

echo -n "msg: "; docker run --name msg -h ${APP_NAME}-msg -e jms=true \
	-e ACCOUNT_NAME=${ACCOUNT_NAME} -e ACCESS_KEY=${ACCESS_KEY} -e EVENT_ENDPOINT=${EVENT_ENDPOINT} \
	-e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} \
	-e NODE_NAME=${APP_NAME}_JMS_NODE -e APP_NAME=$APP_NAME -e TIER_NAME=Order-Processing-Services \
	-e SIM_HIERARCHY_1=${SIM_HIERARCHY_1} -e SIM_HIERARCHY_2=${SIM_HIERARCHY_2} \
	--link db:db --link jms:jms --link oracle-db:oracle-db --link fulfillment:fulfillment -d appdynamics/ecommerce-tomcat:$VERSION

sleep 30

echo -n "angular: "; docker run --name angular -h ${APP_NAME}-angular \
	--link lbr:lbr -p 8080:8080 -d appdynamics/ecommerce-angular:$VERSION

sleep 30

#echo -n "load-gen: "; docker run --name=load-gen --link lbr:lbr --link angular:angular -d appdynamics/ecommerce-load
#echo -n "dbagent: "; docker run --name dbagent \
#        -e CONTROLLER=${CONTR_HOST} -e APPD_PORT=${CONTR_PORT} -e ACCESS_KEY=${ACCESS_KEY} \
#        --link db:db --link oracle-db:oracle-db -d appdynamics/ecommerce-dbagent:$VERSION

# Start MachineAgents
USE_RPM=$(docker exec web ls /etc/init.d/appdynamics-machine-agent 2> /dev/null)

if [ -z ${USE_RPM} ]; then
  EXEC_ARGS=""
elif [ ${USE_RPM} == "/etc/init.d/appdynamics-machine-agent" ]; then
  EXEC_ARGS="-t"
else
  EXEC_ARGS=""
fi

echo "Starting machine agent on web container..."; docker exec ${EXEC_ARGS} web /start-machine-agent.sh; echo "Done"

echo "Starting machine agent on web1 container..."; docker exec ${EXEC_ARGS} web1 /start-machine-agent.sh; echo "Done"

echo "Starting machine agent on ws container..."; docker exec ${EXEC_ARGS} ws /start-machine-agent.sh; echo "Done"

echo "Starting machine agent on msg container..."; docker exec ${EXEC_ARGS} msg /start-machine-agent.sh; echo "Done"

echo "Starting machine agent on fulfillment container..."; docker exec ${EXEC_ARGS} fulfillment /start-machine-agent.sh; echo "Done"

echo "Starting machine agent on fulfillment-client container..."; docker exec ${EXEC_ARGS} fulfillment-client /start-machine-agent.sh; echo "Done"

sleep 60

echo "Starting machine agent on lbr container..."; docker exec ${EXEC_ARGS} lbr /start-machine-agent.sh; echo "Done"

# Set EUM
#echo "Updating RUM Key on lbr..."; docker exec lbr /update-rum-key.sh -k AD-AAB-AAB-HWX -c col.eum-appdynamics.com; echo "Done"
echo "Updating RUM Key on lbr..."; docker exec lbr /update-rum-key.sh ${RUM_KEY} ${EUM_ENDPOINT}; echo "Done"
echo "Updating RUM Key on Angular..."; docker exec angular /update-rum-key.sh ${RUM_KEY} ${EUM_ENDPOINT}; echo "Done"

# Inject new adrum
if [ -e '/root/adrum.js' ]; then
	echo "Injecting adrum..."; sh /root/injectAdrum.sh; echo "Done"
else
	echo "No adrum.js present"
fi

exit 0
