#!/bin/bash
# Building Reporter Startpt Script
#

case $1 in
    start)
        /bin/bash /opt/brmi/brmi_start.sh
    ;;
    stop)
        /bin/bash /opt/brmi/brmi_stop.sh
    ;;
    restart)
        /bin/bash /opt/brmi/brmi_start.sh
        /bin/bash /opt/brmi/brmi_stop.sh
    ;;
esac
exit 0