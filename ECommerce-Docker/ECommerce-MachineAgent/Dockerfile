FROM appddemo/ecommerce-java:oracle-java7

RUN mkdir -p /appdynamics/agents/machine-agent && mkdir /appdynamics/logs
ADD MachineAgent.zip /
RUN unzip -oq /MachineAgent.zip -d /appdynamics/agents/machine-agent
RUN rm MachineAgent.zip

RUN mkdir /appdynamics/shared-logs 
ADD startup.sh /appdynamics
RUN chmod +x /appdynamics/startup.sh

CMD ["/appdynamics/startup.sh"]
EXPOSE 9090
