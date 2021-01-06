#!/bin/bash



S=0

for F in *; do

    I=`stat $F |grep "^Device: "|sed "s/.*[ \t]Inode:[\t ]//"|sed "s/[ \t].*//"`

    S=`expr $S + $I`

done


echo $S
