ECommerce
================
Docker and Java files for ECommerce in the demo environment

Building the Container Images
-----------------------------
Two build scripts exist :

1. demo-main-images.sh -  This is used to build the images without agents - e.g. the images that are infrequently updated. The variables at the top of the file are the required bits for the build (e.g. Java and Oracle). The resulting images are tagged based on the date of the build in the format of yy-mm-dd (16-05-23)

2. demo-build.sh - This is used to build the images which require agents, and are more frequently updated. Variables at the top for paths to the agents, as well as Tomcat and agent version. The agent version is combined with the current date (yy-mm-dd) to tag the image. 

Running the App
-----------------------------
demo-run.sh will start up all the containers, and the neccessary information about the contorller and other aspects of the app are captured in variables at the top. 

Notes about the Architecture
-----------------------------
The application containers and external service containers (database) do NOT run a machine agent. A machine agent should be run on underlying instance/OS and correlated with the application agents via the uniqueHostId - this is the purpose of UNIQUE_HOST_ID variable in demo-run.sh. 

The machine-agent container runs an analytics enabled machine which all the other containers use for transaction and log analytics. The container exposes 9090 for transaction analytics, and for log analytics, demo-run.sh leverages volume mounting to have specific log files shared between the machine-agent container and the respective app containers. This is the purpose of SHARED_LOGS variable in the bash script.  
