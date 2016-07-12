#!/bin/bash

docker exec -i lbr bash -c 'cat > /opt/rh/httpd24/root/var/www/html/adrum.js' < adrum.js
docker exec -i angular bash -c 'cat > /tomcat/webapps/angular/js/adrum.js' < adrum.js
