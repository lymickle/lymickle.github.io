package com.atguigu.day2


import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.table.runtime.aggregate.AggregateAggFunction
import org.apache.flink.types.Row

object AvgAggrateFunction {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val stream = env.addSource(new SensorSource)
    val keyedStream = stream.keyBy(_.id).timeWindow((Time.seconds(5)))
      .aggregate(new AvgTempAgg).print()
    env.execute()
  }

  class AvgTempAgg extends AggregateFunction[SensorReading,(String,Long,Double),(String,Double)]
  {
    override def add(value: SensorReading, accumulator: (String, Long, Double)): (String, Long, Double) =

      {
        (value.id,accumulator._2+1,accumulator._3+value.temperature)
      }



    override def createAccumulator(): (String, Long, Double) = ("",0L,0.0)

    override def getResult(accumulator: (String, Long, Double)): (String, Double) =
      {
        (accumulator._1,accumulator._3/accumulator._2)
      }

    override def merge(a: (String, Long, Double), b: (String, Long, Double)): (String, Long, Double) =
      {
        (a._1+b._1,a._2+b._2,a._3+b._3)
      }
  }
}
