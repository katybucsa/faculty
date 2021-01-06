#!/bin/bash





CMD=""

ARG=""

for C in $*; do

    if [ "$C" == ";" ]; then

      if [ "$CMD" != "" ]; then

        $CMD $ARG

      fi

      CMD=""

      ARG=""

    elif [ "$CMD" == "" ]; then

      CMD=$C

    else

        ARG="$ARG $C"

    fi

done


if [ "$CMD" != "" ]; then

  $CMD $ARG
fi
