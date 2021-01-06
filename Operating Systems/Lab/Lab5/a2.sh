#!/bin/bash




C=""

while [ -n "$1" ]; do

    if [ "$1" == ";" ]; then

      if [ "$C" != "" ]; then

        $C

      fi

      C=""

    else

        C="$C $1"

    fi

    shift

done


if [ "$C" != "" ]; then

  $C

fi

