#!/bin/bash
# Implement a UNIX Shell script that reads from the user a number
# and a string and stops when the string has the same length as the
# number.

while read N S; do
    L=`echo $S|wc -m` # L=${#S}
    L=`expr $L - 1`
    if [ $L -eq $N ]; then
      break
    fi
done
