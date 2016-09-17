import com.databricks.spark.csv._
import com.databricks.spark.avro._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import java.text.SimpleDateFormat
import java.util.{TimeZone, Calendar, Date, Locale}
import org.apache.spark.sql.SQLContext

object JSON2Parquet{
	
	def main(args: Array[String]): Unit = {
	   if (args.length != 2) {
	      System.err.println(s"""
	        |Usage: JSON2Parquet <sourceuUrl> <destinationUrl> 
	        |  <sourceUrl> is the HDFS source location of the JSON files (eg: hdfs:///json_files/*/*)
	        |  <destinationUrl> is the HDFS destination of the parquet files. Will be suffixed by the current day (eg: hdfs://parquet_files/ Path will be: hdfs://parquet_files/YYYY-MM-DD/)  
	        """.stripMargin)
	      System.exit(1)
	    }
		//Create spark context
		val conf = new SparkConf().setMaster("yarn-client").setAppName("JSON2Parquet")
		//read input variables
		val Array(sourceUrl, destinationUrl) = args
		
		
		val sc = new SparkContext(conf)
		val sqlContext = new org.apache.spark.sql.SQLContext(sc)
		val now = Calendar.getInstance()
		val month = now.get(Calendar.MONTH)+1
		val date = now.get(Calendar.YEAR)+"-"+month+"-"+now.get(Calendar.DAY_OF_MONTH)
		val json_read = sqlContext.read.json(sourceUrl)
		json_read.write.parquet(destinationUrl+date)
		      
	}
}