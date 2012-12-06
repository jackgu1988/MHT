#!/bin/bash

iptables -t nat -A PREROUTING -p tcp --destination-port 80 -j REDIRECT --to-ports 10000
mv /etc/etter.conf /etc/etter.conf.bak
cp etter.conf /etc/etter.conf
ifconfig eth0 mtu 7200
ifconfig wlan0 mtu 2274
echo 1 > /proc/sys/net/ipv4/ip_forward

