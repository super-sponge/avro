#!/bin/bash
source ~/.bashrc


libdir=/home/sponge/IdeaProjects/avro/target/lib
avroversion=1.7.4

#for jar in /home/sponge/IdeaProjects/avro/target/lib/*jar
#do
#HADOOP_CLASSPATH=$HADOOP_CLASSPATH:$jar
#done



LIBJARS=${libdir}/avro-${avroversion}.jar,${libdir}/avro-mapred-${avroversion}-hadoop2.jar,${libdir}/paranamer-2.3.jar
export HADOOP_CLASSPATH=${libdir}/avro-${avroversion}.jar:${libdir}/avro-mapred-${avroversion}-hadoop2.jar:${libdir}/paranamer-2.3.jar

hdfs dfs -rm -r /tmp/output
yarn jar /home/sponge/IdeaProjects/avro/target/avro-1.0-SNAPSHOT.jar  example.MapReduceColorCount -libjars ${LIBJARS} /tmp/input /tmp/output
