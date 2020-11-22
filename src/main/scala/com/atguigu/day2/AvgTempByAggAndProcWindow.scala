package com.atguigu.day2


import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.table.runtime.aggregate.AggregateAggFunction
import org.apache.flink.types.Row
import org.apache.flink.util.Collector

object AvgTempByAggAndProcWindow {
  case class AvgInfo(id:String,avgTemp:Double,WindowStart:Long,WindowEnd:Long)
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val stream = env.addSource(new SensorSource)
    val keyedStream = stream.keyBy(_.id).timeWindow((Time.seconds(5)))
      .aggregate(new AvgTempAgg ,new WindoResult).print()
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

  class WindoResult extends ProcessWindowFunction[(String,Double),AvgInfo,String,TimeWindow]
  {
    override def process(key: String, context: Context, elements: Iterable[(String, Double)], out: Collector[AvgInfo]): Unit =
      {
        out.collect(AvgInfo(key,elements.head._2,context.window.getStart,context.window.getEnd))
      }
  }
}
