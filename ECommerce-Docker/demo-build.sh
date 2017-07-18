# VARIABLES
LOCAL_TOMCAT=

(cp ${LOCAL_TOMCAT} ECommerce-Tomcat/apache-tomcat.tar.gz)
echo "Copied Tomcat for ECommerce-Tomcat"

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

cd ECommerce-LBR && docker build --no-cache -t appddemo/ecommerce-lbr .
cd ../ECommerce-Tomcat && docker build --no-cache -t appddemo/ecommerce-tomcat .
cd ../ECommerce-MachineAgent && docker build --no-cache -t appddemo/machineagent-analytics .
