#!/bin/bash




file /usr/bin/* | awk -F: '$2 ~ /^ *ELF/ { print $1}'
