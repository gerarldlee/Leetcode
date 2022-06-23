#!/bin/sh

# prints the second column of sample.txt

# use awk to print the 2nd col
awk '{ print $2 }' sample.txt

# use cut
cut -f 2 -d ' ' sample.txt