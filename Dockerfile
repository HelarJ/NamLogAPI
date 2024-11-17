FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY /target/NamLogAPI-0.0.1-SNAPSHOT.jar /opt/app/NamLogAPI.jar
CMD ["java", "-jar", "/opt/app/NamLogAPI.jar"]