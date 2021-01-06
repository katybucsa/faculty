#!/bin/bash




S=0

for F in *; do

    I=`stat --printf="%i" $F`

    S=`expr $S + $I`

done


echo $S
