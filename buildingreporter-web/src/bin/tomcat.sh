# Tomcat auto-start
#
# description: Auto-starts tomcat
# processname: tomcat
# pidfile: /var/run/tomcat.pid

export JAVA_HOME=/opt/jdk7

case $1 in
start)
sh /opt/tc/bin/startup.sh
;;
stop) 
sh /opt/tc/bin/shutdown.sh
;;
restart)
sh /opt/tc/bin/shutdown.sh
sh /opt/tc/bin/startup.sh
;;
esac 
exit 0
