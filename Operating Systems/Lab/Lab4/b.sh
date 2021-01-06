#!/bin/bash


find a -type f -exec ls -l {} \; | 
	awk -f b.awk
