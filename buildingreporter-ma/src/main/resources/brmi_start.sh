#!/bin/bash

ln -s /dev/ttyACM0 /dev/ttyUSB0
/opt/jdk7/bin/java -jar /opt/brmi/buildingreporter-ma-all.jar 
