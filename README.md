学习研究AVRO相关技术
======================

## 开发avro mapreduce 程序注意事项

* maven 中引入avro-mapred包时如果采用mr2/yarn需要指定<classifier>hadoop2</classifier>
   <dependency>
            <groupId>org.apache.avro</groupId>
            <artifactId>avro-mapred</artifactId>
            <version>${avro.version}</version>
            <classifier>hadoop2</classifier>
   </dependency>
* 在提交到hadoop集群上时请参考script 下面的run.sh脚本执行
* hadoop依赖需要包含 hadoop-mapreduce-client-core hadoop-mapreduce-client-common hadoop-mapreduce-client-jobclient
* avro 的版本一定要与hadoop对应版本一致本实例使用的是hadoop 2.6.0 对应的avro版本是 1.7.4

## 参考文档

* http://avro.apache.org/docs/1.7.7/mr.html
* http://www.cloudera.com/content/cloudera/en/documentation/cdh4/latest/CDH4-Installation-Guide/cdh4ig_topic_26_5.html
* http://www.tuicool.com/articles/7fa2Er
* http://www.tuicool.com/articles/NJZ3Mb

## 引用



The Avro MapReduce API is an Avro module for running MapReduce programs which produce or consume Avro data files.

If you are using Maven, simply add the following dependency to your POM:

<dependency>
    <groupId>org.apache.avro</groupId>
    <artifactId>avro-mapred</artifactId>
    <version>1.7.4</version>
    <classifier>hadoop2</classifier>
</dependency>

Then write your program using the Avro MapReduce javadoc for guidance.

At runtime, include the avro and avro-mapred JARs in the HADOOP_CLASSPATH; and the avro, avro-mapred and paranamer JARs in -libjars.

To enable Snappy compression on output files call AvroJob.setOutputCodec(job, "snappy") when configuring the job. You will also need to include the snappy-java JAR in -libjars.
