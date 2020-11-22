package com.atguigu.day2

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.scala._

object ConsumeFromSensorSource {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val stream = env.addSource(new SensorSource)

    stream.map(new MapFunction[SensorReading,String] {
      override def map(value: SensorReading): String =
        {
          value.id
        }
    }).print()
    env.execute()
  }

  class MyMapFunction extends MapFunction[SensorReading,String]
  {
    override def map(value: SensorReading): String =
      {
        value.id
      }
  }

}
