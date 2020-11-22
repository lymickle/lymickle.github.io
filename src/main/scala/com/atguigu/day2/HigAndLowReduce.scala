package com.atguigu.day2


import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector



object HigAndLowReduce {
  case class MinMaxTemp(id:String,MinTemp:Double,MaxTemp:Double,WindowEnd:Long)
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)

    val stream = env.addSource(new SensorSource)
    stream.map(r => (r.id, r.temperature, r.temperature))
      .keyBy(_._1)
      .timeWindow(Time.seconds(5))
      .reduce((r1: (String, Double, Double), r2: (String, Double, Double)) => (r1._1, r1._2.min(r2._2), r1._3.max(r2._3)), new WindowProcess).print()
    env.execute()

  }
    class HighAndLowAgg extends AggregateFunction[SensorReading, (String, Double, Double), (String, Double, Double)] {

      override def createAccumulator(): (String, Double, Double) = ("", Double.MaxValue, Double.MinValue)

      override def add(value: SensorReading, accumulator: (String, Double, Double)): (String, Double, Double) = {
        (value.id, value.temperature.min(accumulator._2), value.temperature.max(accumulator._3))
      }

      override def getResult(accumulator: (String, Double, Double)): (String, Double, Double) = {
        accumulator
      }

      override def merge(a: (String, Double, Double), b: (String, Double, Double)): (String, Double, Double) = {
        (a._1, a._2.min(b._2), a._3.max(b._3))
      }
    }
    class WindowProcess extends ProcessWindowFunction[(String, Double, Double), MinMaxTemp, String, TimeWindow] {

      override def process(key: String, context: Context, elements: Iterable[(String, Double, Double)], out: Collector[MinMaxTemp]): Unit = {
        val minMax = elements.head
        out.collect(MinMaxTemp(key, minMax._2, minMax._3, context.window.getEnd))
      }
    }

}