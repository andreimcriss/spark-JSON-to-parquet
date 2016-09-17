# Spark JSON 2 Parquet

Simple self-contained application that converts multiple JSON files into Parquet and saves the result in HDFS.

Destination path is suffixed with the current date.(YYYY-MM-DD)


To compile use:
<code>
sbt package
</code>

To submit this job use:

<code>
spark-submit --name SparkHDFSJson2Parquet --class JSON2Parquet  /PATH/TO/JAR/json-to-parquet_2.10-1.0.jar HDFS:///SOURCE_PATH/ HDFS://DESTINATION_PATH/
</code>
