FROM openjdk:11-jre-slim

WORKDIR /home/ec2-user/

COPY itamin-backend-0.0.1-SNAPSHOT.jar .

CMD java -jar itamin-backend-0.0.1-SNAPSHOT.jar