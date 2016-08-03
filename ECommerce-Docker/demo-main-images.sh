####  path to oracle db and oracle jdk
ORACLE_DB_RPM=
ORACLE_JAVA=

#### Copy files over
cp ${ORACLE_DB_RPM} ./ECommerce-Oracle
cp ${ORACLE_JAVA} ./ECommerce-Java/jdk-linux-x64.rpm

#### Build images
DATE=`date +%y.%m.%d`
cd ECommerce-Oracle
docker build -t appddemo/ecommerce-oracle:${DATE} .
cd ../ECommerce-MySQL
docker build -t appddemo/ecommerce-mysql:${DATE} .
cd ../ECommerce-Java
docker build -t appddemo/ecommerce-java:${DATE} .
cd ../ECommerce-ActiveMQ
docker build -t appddemo/ecommerce-activemq:${DATE} .


