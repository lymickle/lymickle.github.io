package com.atguigu.day2

import org.apache.flink.streaming.api.functions.co.{CoFlatMapFunction, CoMapFunction}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector

object CoMapExample {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val stream1:DataStream[(String,Int)] = env.fromElements(("zhangsan",130),("lisi",140))
    val stream2 = env.fromElements(("zhangsan",26),("lisi",33))
    val connected =  stream1.keyBy(_._1).connect(stream2.keyBy(_._1))
    connected.map(new myCoMapFunction)
    connected.flatMap(new MyCoFlatmap).print()
    env.execute()
  }
  class myCoMapFunction extends CoMapFunction[(String,Int),(String,Int),String]
  {
    override def map1(value: (String, Int)): String =
      {
         value._1+"'s weight is "+ value._2
      }

    override def map2(value: (String, Int)): String =
      {
        value._1+"'s age is "+ value._2
      }
  }
  class MyCoFlatmap extends CoFlatMapFunction[(String,Int),(String,Int),String]
  {
    override def flatMap1(value: (String, Int), out: Collector[String]): Unit =
      {
        out.collect(value._1+"'s weight is "+ value._2)
        out.collect(value._1+"'s weight is "+ value._2)
      }

    override def flatMap2(value: (String, Int), out: Collector[String]): Unit =
      {
        out.collect( value._1+"'s age is "+ value._2)
      }
  }
}
