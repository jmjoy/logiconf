FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/logiconf.jar /logiconf/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/logiconf/app.jar"]
