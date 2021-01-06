#!/bin/bash




sed "s/\(\([a-z][0-9]\)\+\)/\n\1\n/gi" c.txt |\
 
grep "^\([a-z][0-9]\)\+$" |\
  
sed "s/[a-z]/ /gi" |\
  
awk -f c.awk
