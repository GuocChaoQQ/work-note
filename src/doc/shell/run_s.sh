#！/bin/bash
date_start=$1
date_end=$2
script_directory=$3

log_directory="/home/admin/asset_report/log"

echo $date_start
echo $date_end
flag=1;
#echo ($date_start != $date_end)
while [[ $flag == 1 ]]
do

if [ $date_start = $date_end ]
then
        flag=2
#else
#echo "正在处理$date_start"
fi;
#echo "正在处理$date_start"

##########################执行指定的批次 =====================
echo "执行批次$date_start"

for i in `ls $script_directory`
do
log_file_name=`basename $i | cut -d'.' -f1`
echo $log_file_name
echo "$log_directory/$log_file_name"
echo "$script_directory/$i"
sh "$script_directory/$i" $date_start >> "$log_directory/$log_file_name" 2>&1

#echo "$i"
done

#echo  `date -d "$date_start +1 day" +%Y-%m-%d`d
date_start=`date -d "$date_start +1 day" +%Y-%m-%d`
done
