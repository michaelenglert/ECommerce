# VARIABLES
ZIP_MACHINE_AGENT=/Users/rbolton/Documents/Code/appd/agents/MachineAgent-4.2.1.7.zip
WEB_SERVER_AGENT=/Users/rbolton/Documents/Code/appd/agents/appdynamics-sdk-native-nativeWebServer-64bit-linux-4.1.5.2.tar.gz
APP_SERVER_AGENT=/Users/rbolton/Documents/Code/appd/agents/AppServerAgent-4.2.1.7.zip
LOCAL_TOMCAT=/Users/rbolton/Documents/Code/appd/agents/apache-tomcat-7.0.70.tar.gz

(cp ${APP_SERVER_AGENT} ECommerce-Tomcat/AppServerAgent.zip)
(cp ${ZIP_MACHINE_AGENT} ECommerce-Tomcat/MachineAgent.zip)
echo "Copied Agents for ECommerce-Tomcat"

cp ${APP_SERVER_AGENT} ECommerce-FulfillmentClient/AppServerAgent.zip
cp ${ZIP_MACHINE_AGENT} ECommerce-FulfillmentClient/MachineAgent.zip
echo "Copied Agents for ECommerce-FulfillmentClient"


cp ${WEB_SERVER_AGENT} ECommerce-LBR/webserver_agent.tar.gz
cp ${ZIP_MACHINE_AGENT} ECommerce-LBR/MachineAgent.zip
echo "Copied Agents for ECommerce-LBR"


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
(cp ../ECommerce-Java/ECommerce-FulfillmentClient/build/libs/ECommerce-FulfillmentClient.jar ECommerce-FulfillmentClient/)
echo "Files copied over"
