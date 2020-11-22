package com.atguigu.day2

import org.apache.flink.streaming.api.scala._

object RedistributeStream {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val defaultP = env.getParallelism
    print(defaultP)
    val stream = env.addSource(new SensorSource).setParallelism(1)
      .map(r=>r.id).setParallelism(2)
    stream.print()
    env.execute()

  }
}
