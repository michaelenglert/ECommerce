# VARIABLES
ZIP_MACHINE_AGENT=/Users/rbolton/Documents/Code/appd/agents/MachineAgent-4.2.5.0.zip
WEB_SERVER_AGENT=/Users/rbolton/Documents/Code/appd/agents/appdynamics-sdk-native-nativeWebServer-64bit-linux-4.2.5.0.tar.gz
APP_SERVER_AGENT=/Users/rbolton/Documents/Code/appd/agents/AppServerAgent-4.2.5.0.zip
LOCAL_TOMCAT=/Users/rbolton/Documents/Code/appd/agents/apache-tomcat-7.0.70.tar.gz
AGENT_VERSION=4.2.5.0

(cp ${APP_SERVER_AGENT} ECommerce-Tomcat/AppServerAgent.zip)
(cp ${LOCAL_TOMCAT} ECommerce-Tomcat/apache-tomcat.tar.gz)
echo "Copied Agent and Tomcat for ECommerce-Tomcat"

cp ${WEB_SERVER_AGENT} ECommerce-LBR/webserver_agent.tar.gz
echo "Copied Agent for ECommerce-LBR"

(cp ${ZIP_MACHINE_AGENT} ECommerce-MachineAgent/MachineAgent.zip)
echo "Copied machine agent to ECommerce-MachineAgent"

#copy and build 
(cd ../ECommerce-Java/ && gradle war uberjar)
(cd ../)

(cp ${LOCAL_TOMCAT} ECommerce-Tomcat/apache-tomcat.tar.gz)
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

DATE=`date +%y.%m.%d`
VERSION="${AGENT_VERSION}-${DATE}"
cd ECommerce-LBR && docker build --no-cache -t appddemo/ecommerce-lbr:$VERSION .
cd ../ECommerce-Tomcat && docker build --no-cache -t appddemo/ecommerce-tomcat:$VERSION .
cd ../ECommerce-MachineAgent && docker build --no-cache -t appddemo/machineagent-analytics:$VERSION .
