#!/bin/bash
# Implement a UNIX Shell script that takes as command line
# arguments directories and calculates their average size.

N=0
S=0
for D in $*; do
    if [ ! -d $D ]; then
       continue
    fi
    N=`expr $N + 1`
    K=`du -s $D|awk '{print $1}'`
    S=`expr $S + $K`
done

echo `expr $S / $N`

