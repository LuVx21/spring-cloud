FROM openjdk:21-slim
WORKDIR /app
ADD target/user-service-consumer-ali-0.1.jar app.jar
ENV JAVA_OPTS="\
-server \
-Xms256m \
-Xmx512m \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=512m \
--enable-preview"
ENV PARAMS=""
EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS app.jar $PARAMS"]
