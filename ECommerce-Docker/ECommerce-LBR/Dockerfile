FROM appddemo/ecommerce-java

# Install Apache 2.4 (Centos 6 uses 2.2 by default)
RUN useradd -U apache
RUN cd /etc/yum.repos.d/; curl -O -k  https://repos.fedorapeople.org/repos/jkaluza/httpd24/epel-httpd24.repo
RUN yum -y install httpd24.x86_64
ENV HTTPD_24 /opt/rh/httpd24/root/etc/httpd
ENV HTTPD_DOC_ROOT ${HTTPD_24}/../../var/www/html

# set timezone to UTC
RUN mv /etc/localtime /etc/localtime.bak
RUN ln -s /usr/share/zoneinfo/Etc/UTC /etc/localtime

# Use Apache Worker MPM
ADD 00-mpm.conf ${HTTPD_24}/conf.modules.d/00-mpm.conf

# Environment settings for startup scripts
ADD env.sh /
RUN chmod +x /env.sh

RUN ldconfig

# Add libappdynamics_native_sdk

# ECommerce reverse proxy configuration
ADD ajp_proxy.conf ${HTTPD_24}/conf.d/

ADD startup.sh /
RUN chmod 744 /startup.sh

# Run startup script and tail Apache error log
CMD /startup.sh && tail -f ${HTTPD_24}/logs/error_log

EXPOSE 80
