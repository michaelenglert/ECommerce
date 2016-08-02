FROM appddemo/ecommerce-java:oracle-java7

# Install Apache 2.4 (Centos 6 uses 2.2 by default)
RUN useradd -U apache
RUN cd /etc/yum.repos.d/; curl -O -k  https://repos.fedorapeople.org/repos/jkaluza/httpd24/epel-httpd24.repo
RUN yum -y install httpd24.x86_64
ENV HTTPD_24 /opt/rh/httpd24/root/etc/httpd
ENV HTTPD_DOC_ROOT ${HTTPD_24}/../../var/www/html

# set timezone to UTC
RUN mv /etc/localtime /etc/localtime.bak
RUN ln -s /usr/share/zoneinfo/Etc/UTC /etc/localtime

ENV APPDYNAMICS_SIM_ENABLED true

# Use Apache Worker MPM
ADD 00-mpm.conf ${HTTPD_24}/conf.modules.d/00-mpm.conf

# Add mod_appdynamics configuration file
ADD 02-appd.conf ${HTTPD_24}/
ADD update-rum-key.sh /
RUN chmod +x /update-rum-key.sh
RUN cp /update-rum-key.sh /usr/local/bin/update-rum-key


# Add native SDK
ENV SDK_HOME /opt
ENV NATIVE_HOME ${SDK_HOME}/appdynamics-sdk-native

# Environment settings for startup scripts
ADD env.sh ${NATIVE_HOME}/
RUN chmod +x ${NATIVE_HOME}/env.sh

ADD webserver_agent.tar.gz ${SDK_HOME}
ADD appdynamics_sdk.conf /etc/ld.so.conf.d/
RUN ldconfig
RUN (cd ${SDK_HOME} gunzip ${SDK_HOME}/webserver_agent.tar.gz)
RUN (cd ${SDK_HOME} tar xf ${SDK_HOME}/webserver_agent.tar)
RUN chmod 777 ${NATIVE_HOME}/logs
RUN chmod 744 ${NATIVE_HOME}/install.sh
RUN ${NATIVE_HOME}/install.sh

# Add libappdynamics_native_sdk
RUN ln -s ${NATIVE_HOME}/sdk_lib/lib/libappdynamics_native_sdk.so /usr/lib64/libappdynamics_native_sdk.so
RUN ln -s ${NATIVE_HOME}/WebServerAgent/Apache/libmod_appdynamics.so $HTTPD_24/modules/mod_appdynamics.so

# ECommerce reverse proxy configuration
ADD ajp_proxy.conf ${HTTPD_24}/conf.d/

# Add utility scripts to /usr/local/bin
ADD tail-proxy-log /usr/local/bin/
ADD tail-access-log /usr/local/bin/
ADD tail-error-log /usr/local/bin/
RUN chmod 744 /usr/local/bin/tail-*

ADD startup.sh $NATIVE_HOME/
RUN chmod 744 ${NATIVE_HOME}/startup.sh
RUN chown -R apache:apache ${NATIVE_HOME}
RUN chmod 7777 -R ${NATIVE_HOME}

# Run startup script and tail Apache error log
CMD ${NATIVE_HOME}/startup.sh && tail -f ${HTTPD_24}/logs/error_log

EXPOSE 80
