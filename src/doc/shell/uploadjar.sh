#!/bin/bash
hdfs_base_path="/user/admin/xxx/"
hdfs_source_path="/user/admin/xxx/"
file_path=$1
echo $file_path
if [ "$file_path" = "" ]
then echo '请输入对应的jar包路径如：/home/xxxx/dw-xx.jar'
exit
fi

echo '---检测对应的jar包名称------------'
jar_name=`basename $file_path`

if [ "$jar_name" = "" ]
then 
echo "请传入对应的jar包路径/jar包名称"
exit
fi
echo $jar_name

"---测试是否在basejar目录下"
`hadoop fs -test -e $hdfs_base_path/$jar_name`
if [ $? -eq 0 ] 
then
echo “替换 $hdfs_base_path/$jar_name”
echo `sudo -u hdfs hdfs dfs -rm $hdfs_base_path/$jar_name`
echo `sudo -u hdfs hdfs dfs -put $file_path $hdfs_base_path`
echo "--$jar_name替换成功!"
exit
fi;


"--测试是否在sourcejar目录下"
`hadoop fs -test -e $hdfs_source_path/$jar_name`
if [ $? -eq 0 ] 
then
echo “替换 $hdfs_source_path/$jar_name”
echo `sudo -u hdfs hdfs dfs -rm $hdfs_source_path/$jar_name`
echo `sudo -u hdfs hdfs dfs -put $file_path $hdfs_source_path`
echo "--$jar_name 替换成功"
exit
fi;







