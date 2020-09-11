#/!/bin/bash
filename=$1
for line in `cat $filename`
do
	hive -e "drop view mysql_view.$line"
 #echo $line
done
