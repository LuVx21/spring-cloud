FROM openjdk:21-slim
WORKDIR /app
ADD ./user-service-dubbo/user-service-provider/target/user-service-provider-0.1.jar app.jar
ENV JAVA_OPTS="\
-server \
-Xms128m \
-Xmx256m \
-XX:MetaspaceSize=128m \
-XX:MaxMetaspaceSize=256m \
--enable-preview"
ENV PARAMS=""
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS app.jar $PARAMS"]
