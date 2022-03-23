FROM openjdk:8-jdk
COPY target/*.jar daydaystudy.jar
EXPOSE 7777
CMD java -Xms8g -Xmx8g -server -XX:+UseG1GC -XX:+PrintGCTimeStamps -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses -XX:+ExplicitGCInvokesConcurrent -XX:+PrintGCDetails -XX:+PrintGCDateStamps -jar daydaystudy.jar
