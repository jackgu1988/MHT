#!/bin/bash

IP=$(/sbin/ip route | awk '/default/ { print $3 }')
INTERFACE=$(/sbin/ip route | awk '/default/ { print $5 }')

#TODO: currently only works for 255.255.255.0 masks
gksu nmap -sP -oX out.xml ${IP%.*}.*
