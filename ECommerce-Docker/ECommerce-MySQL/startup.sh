#!/bin/bash

# Start SSH
sudo /etc/init.d/ssh start

# Start MySQL
service mysql start

mysql -uroot -psingcontroller -e "grant all on *.* to 'root'@'%' IDENTIFIED BY 'singcontroller'"

service mysql restart
exit 0
