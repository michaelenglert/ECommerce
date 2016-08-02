####  path to oracle db and oracle jdk
ORACLE_DB_RPM=/Users/rbolton/Documents/Code/appd/agents/oracle-xe-11.2.0-1.0.x86_64.rpm
ORACLE_JAVA=/Users/rbolton/Documents/Code/appd/agents/jdk-7u79-linux-x64.rpm

#### Copy files over
cp ${ORACLE_DB_RPM} ./ECommerce-Oracle
cp ${ORACLE_JAVA} ./ECommerce-Java/jdk-linux-x64.rpm

#### Build images
cd ECommerce-Oracle
docker build -t appddemo/ecommerce-oracle:latest .
cd ../ECommerce-MySQL
docker build -t appddemo/ecommerce-mysql:latest .
cd ../ECommerce-Java
docker build -t appddemo/ecommerce-java:oracle-java7 .
cd ../ECommerce-ActiveMQ
docker build -t appddemo/ecommerce-activemq .


