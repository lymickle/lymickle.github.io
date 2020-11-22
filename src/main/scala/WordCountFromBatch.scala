package com.atguigu
import org.apache.flink.streaming.api.scala._

object WordCountFromBatch {
  case class WordWithCount(word: String,count:Int)

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val stream = env.fromElements("hello world","hello world","hello world")

    val transformed = stream.flatMap(line=>line.split(" "))
      .map(w=>WordWithCount(w,1))
      .keyBy(0)
      .sum(1)
    transformed.print()
    env.execute()
  }

}
