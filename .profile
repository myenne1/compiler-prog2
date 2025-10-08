PATH=~cs4351_bau/bin:$PATH
export CS4351=/classes/cs4351/cs4351_bau/pub
export PROG=chap4
export TIGER=${CS4351}/tiger
export CLASSPATH=.:..:${CS4351}/classes/${PROG}:${CS4351}/classes
umask 0077
clear
echo "Kernal: `uname -sr`"
echo "OS Version: `uname -v`"
echo
cat /etc/motd.profile
echo
