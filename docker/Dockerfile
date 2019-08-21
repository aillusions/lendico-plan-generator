FROM maven:3.5-jdk-8

WORKDIR /usr/src/app

COPY ./pom.xml /usr/src/app/pom.xml

RUN  mvn dependency:go-offline

COPY ./src /usr/src/app/src

RUN  mkdir /usr/src/app/logs \
   && mvn clean package -DskipTests=true \
   && cp /usr/src/app/target/PlanGenerator-0.0.1-SNAPSHOT.jar /usr/src/app/PlanGenerator.jar

COPY ./docker/run.sh /usr/src/app/run.sh
RUN chmod +x /usr/src/app/run.sh

CMD ["/usr/src/app/run.sh"]