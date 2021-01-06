#!/bin/bash




CMD=""

ARG=""

for C in $*; do

    if test "$C" == ";" ; then

      if test -n "$CMD"; then

        $CMD $ARG

      fi

      CMD=""

      ARG=""

    elif test -z "$CMD"; then

      CMD=$C

    else

        ARG="$ARG $C"

    fi

done


if test -n "$CMD"; then

  $CMD $ARG
fi
