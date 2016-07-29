FROM appddemo/ecommerce-java:oracle-java7

RUN export TERM=xterm

# set timezone to UTC
RUN mv /etc/localtime /etc/localtime.bak
RUN ln -s /usr/share/zoneinfo/Etc/UTC /etc/localtime

# Environment vars: Tomcat
ENV CATALINA_HOME /tomcat

# Environment settings for startup scripts
ADD env.sh /
RUN chmod +x /env.sh

# Install Tomcat
COPY /apache-tomcat.tar.gz /apache-tomcat.tar.gz
RUN tar zxf /apache-tomcat.tar.gz \
    && rm apache-tomcat.tar.gz \
    && mv apache-tomcat* tomcat
RUN cd ${CATALINA_HOME}/bin;chmod +x *.sh

# App Server Agent Install
ADD AppServerAgent.zip /
RUN unzip -q /AppServerAgent.zip -d ${CATALINA_HOME}/appagent;rm AppServerAgent.zip

RUN mkdir -p /ECommerce-Java/ECommerce-Web/build/libs
RUN mkdir -p /ECommerce-Java/ECommerce-JMS/build/libs
RUN mkdir -p /ECommerce-Java/ECommerce-WS/build/libs

ADD appdynamicspilot.war /ECommerce-Java/ECommerce-Web/build/libs/
ADD appdynamicspilotjms.war /ECommerce-Java/ECommerce-JMS/build/libs/
ADD cart.war /ECommerce-Java/ECommerce-WS/build/libs/

RUN mkdir -p /ECommerce-Java/libs
ADD ojdbc6.jar /ECommerce-Java/ECommerce-Web/libs/

ADD build.gradle /ECommerce-Java/
ADD database.properties /ECommerce-Java/
ADD schema.sql /ECommerce-Java/
ADD oracle.sql /ECommerce-Java/
ADD zips.sql /ECommerce-Java/

# Startup scripts
ADD startup.sh /
RUN version=`find ${CATALINA_HOME}/appagent -maxdepth 1 -type d -name "ver*" | sed "s:^${CATALINA_HOME}/appagent/::"`; \
    sed -e "s:_VERSION_STRING:$version:g" env.sh > env.sh.tmp && mv env.sh.tmp env.sh
ADD start-machine-agent.sh /
ADD start-appserver-agent.sh /
ADD app-agent-config-dbcam.xml /

RUN chmod +x /startup.sh
RUN chmod +x /start-machine-agent.sh
RUN chmod +x /start-appserver-agent.sh
#RUN chmod +x /configAnalytics.sh

RUN export TERM=xterm

WORKDIR /

# Note: This command should not return or the container will exit
CMD "/startup.sh"

EXPOSE 80
EXPOSE 8080
EXPOSE 8009
