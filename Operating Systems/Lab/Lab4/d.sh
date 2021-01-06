#!/bin/bash




grep -o "\([a-z][0-9]\)\+" c.txt |\
  
sed "s/[a-z]/ /gi" |\
  
awk -f c.awk
