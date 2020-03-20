#!/usr/bin/env bash

# 使用maven命令去进行部署

mvn clean install -Dmaven.test.skip=true

#java -jar /target/calculator-1.0-SNAPSHOT.jar

mvn exec:java -Dexec.mainClass="com.leewake.Main"

