#!/bin/bash


# We are using \ at the end of the line to escape

# the new line, and thus make the command

# multiline, which is more readable. If we delete

# the \, then we need to have it all on a single

# line


find a -type f -exec ls -l {} \; |\
 
 	awk '{print $5}' |\
  
	sort -n |\
 
        head -n -1 |\
  
	tail -n +2 |\

 	awk '{n=n+$1} END{print (n/NR)}'
