package batch

import java.lang.management.ManagementFactory

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by jose on 01/03/17.
  */
object BatchJob {

  def main(args: Array[String]): Unit = {

    // Get Spark Configuration
    val conf = new SparkConf()
      .setAppName("Lambda with Spark")

    // We'll run Spark using the local cluster manager.
    // Remember we could be using Mesos or YARN
    // We check if we're running from the IDE to set the master. Otherwise it would be provided?
    if (ManagementFactory.getRuntimeMXBean.getInputArguments.toString.contains("IntelliJ IDEA")) {
      // For Windows only. System.setProperty("hadoop.home.dir", "F:\\Libraries\\WinUtils")

      // The * indicates Spark to use all the cores available on the host machine
      conf.setMaster("local[*]")
    }


    // Setup Spark Context. The Context is the heart of Spark
    val sc = new SparkContext(conf)

    // Create a RDD from the sample stream
    val sourceFile = "/Users/jco59/ML/WorkingData/LambdaArchitecture/spark-kafka-cassandra-applying-lambda-architecture/vagrant/data.tsv"

    // We are building the graph not executing it yet
    // Transformations are added to the graph
    val input = sc.textFile(sourceFile)

    // Actions make the graph to be executed
    input.foreach(println)


  }
}
