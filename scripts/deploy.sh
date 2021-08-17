#! /bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=MySpringBootProject

echo "> Copy Build files"
cp $REPOSITORY/zip/*.jar $REPOSITORY/.file

echo "> Check pid"
CURRENT_PID=$(pgrep -fl MySpringBootProject || grep jar | awk '{print $1}')

echo "> current pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> No Running Application"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo " Deploy New Application"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> Jar Name : $JAR_NAME"

echo "> Allow Permission to $JAR_NAME"
chmod +x $JAR_NAME

echo "> Run $JAR_NAME"
nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec-2user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
  -Dspring.profiles.active=real \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &