FROM openjdk:21-slim
WORKDIR /app
ADD ./user-service-consumer-ali/target/user-service-consumer-ali-0.1.jar app.jar
ENV JAVA_OPTS="\
-server \
-Xms128m \
-Xmx256m \
-XX:MetaspaceSize=128m \
-XX:MaxMetaspaceSize=256m \
--enable-preview"
ENV PARAMS=""
EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS app.jar $PARAMS"]
