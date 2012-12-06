#!/bin/bash

iptables -t nat -D PREROUTING -p tcp --destination-port 80 -j REDIRECT --to-ports 10000
mv /etc/etter.conf.bak /etc/etter.conf
echo 0 > /proc/sys/net/ipv4/ip_forward