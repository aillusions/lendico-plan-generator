#!/bin/bash

cd /usr/src/app

java -Xms200m -Xmx300m -XX:MetaspaceSize=76m \
    -server \
    -XX:+UseParallelGC \
    -Djava.endorsed.dirs=/usr/local/tomcat7/endorsed \
    -Djava.io.tmpdir=/usr/src/app \
    -Djavax.servlet.request.encoding=UTF-8 \
    -XX:+PrintGCDetails \
    -XX:+PrintTenuringDistribution \
    -XX:+PrintGCApplicationStoppedTime \
    -XX:+PrintGCDateStamps \
    -XX:+PrintGCTimeStamps \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=/usr/src/app \
    -Xloggc:/usr/src/app/gc.log \
    -Dfile.encoding=UTF-8 \
    -jar PlanGenerator.jar