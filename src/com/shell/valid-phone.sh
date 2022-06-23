#!/bin/sh

# given phone.txt
#987-123-4567
#123 456 7890
#(123) 456-7890

# print only valid phone numbers with format xxx-xxx-xxxx or (xxx) xxx-xxxx

# use grep + regex
# print matching lines
grep -P '^(\d{3}-|\(\d{3}\) )\d{3}-\d{4}$' phone.txt

# grep
#egrep - extended regex
#fgrep - fixed string grep
#pgrep - perl regex grep


# use awk
# print matching lines
awk '/^((\([[:digit:]]{3}\)) |[[:digit:]]{3}-)[[:digit:]]{3}-[[:digit:]]{4}$/ {print $0}' phone.txt

# use sed
# delete the lines which are mismatch
sed '/^\(([0-9]\{3\}) \|[0-9]\{3\}-\)[0-9]\{3\}-[0-9]\{4\}$/!d' phone.txt