package com.atguigu.day2

import org.apache.flink.streaming.api.scala._

object KeyedStreamExample {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val stream = env.addSource(new SensorSource).filter(_.id.equals("sensor_1"))
    val keyed = stream.keyBy(_.id)
    keyed.reduce((r1,r2)=>SensorReading(r1.id,0,r1.temperature.min(r2.temperature))).print()
    env.execute()
  }
}
