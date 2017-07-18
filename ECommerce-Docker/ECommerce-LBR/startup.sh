#!/bin/bash

# Read env vars for Controller, Port, App, Tier and Node names
source /env.sh
/etc/init.d/httpd24-httpd stop

# Set Apache ServerName using container's hostname
echo "ServerName `hostname`:80" >> ${HTTPD_24}/conf/httpd.conf

/etc/init.d/httpd24-httpd start
