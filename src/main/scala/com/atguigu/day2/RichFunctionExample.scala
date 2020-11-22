package com.atguigu.day2

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala._

object RichFunctionExample {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)
    val stream = env.fromElements("hello world","hello atguigu ")
    stream.map(new MyRichMap).print()
    env.execute()

  }
  class MyRichMap extends RichMapFunction[String,String]
  {
    override def open(parameters: Configuration): Unit =
      {
        print("starting ")
      }
    override def map(value: String): String =
      {
        val name = getRuntimeContext.getTaskName
        "task name is :"+name
      }

    override def close(): Unit = {
      print("closing")
    }
  }

}
