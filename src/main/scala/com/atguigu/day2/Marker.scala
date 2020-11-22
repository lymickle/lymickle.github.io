package com.atguigu.day2

import scala.collection.mutable

object Marker {
  val markers = mutable.Map(
    "red"->new Marker("red"),
    "blue"->new Marker("blue"),
    "yellow"->new Marker("yellow")
  )
  def getMarker( color:String)
  {
    markers.getOrElse(color,new Marker(color))
  }
  def main(args: Array[String]): Unit = {
    println(Marker.getMarker("red"))
    println(Marker.getMarker("blue"))
    println(Marker.getMarker("yellow"))
    println(Marker.getMarker("grape"))
  }
}


class Marker  private (val color:String)
{
  println(s"$color marker")

  override def toString: String = s"color"
}