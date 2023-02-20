FROM amazoncorretto:11-alpine-jdk
MAINTAINER AriadnaLoboFS
COPY target/mgb-0.0.1-SNAPSHOT.jar   ary-app.jar
ENTRYPOINT ["java","-jar","/ary-app.jar"]
