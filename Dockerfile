FROM openjdk:11-jre-slim
WORKDIR /app

COPY build/libs/service3-1.0-SNAPSHOT-microbundle.jar /app/service3-1.0-SNAPSHOT-microbundle.jar


CMD ["java", "-jar", "service3-1.0-SNAPSHOT-microbundle.jar"]