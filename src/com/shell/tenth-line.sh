#!/bin/sh

# prints the 2nd line of the sample.txt file

# use sed

# print line 2 and 5
sed -n '2p;5p' < sample.txt

# sed prints range from line 2 to line 5
sed -n '2,5p' < sample.txt