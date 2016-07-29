FROM ubuntu

MAINTAINER Jeff Morgan (jeff.morgan@appdynamics.com)

################## BEGIN INSTALLATION ######################

# Install Packages
RUN apt-get update
RUN echo "mysql-server-5.7 mysql-server/root_password password singcontroller" | debconf-set-selections
RUN echo "mysql-server-5.7 mysql-server/root_password_again password singcontroller" | debconf-set-selections
RUN apt-get -yq install mysql-server-5.7
RUN apt-get -y install openssh-server

# Setup SSH
RUN mkdir /var/run/sshd
RUN echo 'root:welcome1' | chpasswd
RUN sed -i 's/PermitRootLogin without-password/PermitRootLogin yes/' /etc/ssh/sshd_config
# SSH login fix. Otherwise user is kicked off after login
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

# Configure MySQL
RUN sed -i '/^bind-address/c\bind-address=0.0.0.0' /etc/mysql/mysql.conf.d/mysqld.cnf

# Add startup script
ADD /startup.sh /root/
RUN chmod 777 /root/startup.sh
RUN chown -R root.root /root/

##################### INSTALLATION END #####################

# Expose Ports
EXPOSE 3306
EXPOSE 22

CMD /root/startup.sh && tail -F /var/log/mysql/error.log
