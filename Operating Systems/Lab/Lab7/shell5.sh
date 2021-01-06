#!/bin/bash

find . -type f | while read F; do
    T=`file -b $F|sed "s/,.*//"`
    S=`du -b $F|awk '{print $1}'`
    echo "$F:$S:$T"
done > data

awk -F: '{print $3}' data|sort|uniq -c

echo

awk -F: '{print $3}' data|\
  sort|\
  uniq|\
  while read T; do
      SUM=`awk -F: -v t="$T" 't == $3 {s=s+$2} END{print s}' data`
      echo $T $SUM
  done

echo

awk -F: '{print $3}' data|\
  sort|\
  uniq|\
  while read T; do
      MAX=`awk -F: -v t="$T" 't == $3 {print $2}' data|sort -n|tail -n 1`
      echo $T $MAX
  done


