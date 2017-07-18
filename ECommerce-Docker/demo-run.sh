#!/bin/bash

echo -n "uninstrumented"; docker run --name uninstrumented -d -p 3000:3000 appddemo/ecommerce-uninstrumented
echo -n "oracle-db: "; docker run --name oracle-db -d -p 1521:1521 -p 2222:22 appddemo/ecommerce-oracle
echo -n "db: "; docker run --name db -e MYSQL_ROOT_PASSWORD=singcontroller -p 3306:3306 -p 2223:22 -d appddemo/ecommerce-mysql
echo -n "jms: "; docker run --name jms -d appddemo/ecommerce-activemq
sleep 30

echo -n "ws: "; docker run --name ws -e create_schema=true -e ws=true \
  --link db:db --link jms:jms --link oracle-db:oracle-db --link uninstrumented:api.mainsupplier.com --link uninstrumented:api.secondarysupplier.com \
	-d appddemo/ecommerce-tomcat

echo -n "web: "; docker run --name web -e JVM_ROUTE=route1 -e web=true \
	--link db:db --link oracle-db:oracle-db --link ws:ws --link jms:jms \
	-d appddemo/ecommerce-tomcat

sleep 30

echo -n "fulfillment: "; docker run --name fulfillment -e web=true \
	--link db:db --link ws:ws --link jms:jms --link oracle-db:oracle-db \
	-d appddemo/ecommerce-tomcat

sleep 30

echo -n "web1: "; docker run --name web1 -e JVM_ROUTE=route2 -e web=true \
	--link db:db --link oracle-db:oracle-db --link ws:ws --link jms:jms \
	-d appddemo/ecommerce-tomcat

sleep 30

echo -n "lbr: "; docker run --name=lbr \
	--link web:web --link web1:web1 \
	-p 80:80 -d appddemo/ecommerce-lbr

echo -n "msg: "; docker run --name msg -e jms=true \
	--link db:db --link jms:jms --link oracle-db:oracle-db --link fulfillment:fulfillment \
	-d appddemo/ecommerce-tomcat

exit 0
