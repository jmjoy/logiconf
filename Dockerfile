FROM ubuntu:16.04
MAINTAINER jmjoy <jmjoy@jmjoy.top>

WORKDIR /root

RUN apt-get update -y && apt-get upgrade -y

RUN apt-get install wget default-jdk -y

# install leiningen
RUN wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein && \
    chmod a+x lein && \
    mv lein /usr/bin/lein

RUN lein

# install bower
RUN apt-get install npm -y
RUN npm install bower -g

EXPOSE 3000
EXPOSE 7000

WORKDIR /project

CMD ["lein", "run"]
