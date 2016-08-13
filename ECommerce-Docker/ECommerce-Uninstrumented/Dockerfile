FROM centos:centos7

RUN \
    yum install -y wget && \
    wget https://nodejs.org/dist/v4.3.1/node-v4.3.1-linux-x64.tar.xz && \
    tar --strip-components 1 -xJvf node-v* -C /usr/local

COPY src /src
RUN npm install -g forever
RUN cd /src; npm install

CMD ["forever", "-l", "/forever.log", "-o", "/out.log", "-e", "/err.log", "-vf", "/src/index.js"]

EXPOSE 3000
