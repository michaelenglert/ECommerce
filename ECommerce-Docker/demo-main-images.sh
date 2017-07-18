####  path to oracle db and oracle jdk
ORACLE_DB_RPM=
ORACLE_JAVA=

#### Copy files over
cp ${ORACLE_DB_RPM} ./ECommerce-Oracle/oracle-xe-11.2.0-1.0.x86_64.rpm
cp ${ORACLE_JAVA} ./ECommerce-Java/jdk-linux-x64.rpm

#### Build images
cd ECommerce-Oracle
docker build -t appddemo/ecommerce-oracle .
cd ../ECommerce-MySQL
docker build -t appddemo/ecommerce-mysql .
cd ../ECommerce-Java
docker build -t appddemo/ecommerce-java .
cd ../ECommerce-ActiveMQ
docker build -t appddemo/ecommerce-activemq .
cd ../ECommerce-Uninstrumented
docker build -t appddemo/ecommerce-uninstrumented .
