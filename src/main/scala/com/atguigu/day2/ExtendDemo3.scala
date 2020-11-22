package com.atguigu.day2

object ExtendDemo3 {
  def main(args: Array[String]): Unit = {
    val circle1:Circle =  new Circle(0,0,4)
    val circle2:Circle =  new Circle(0,4,5)

    println( circle1.distance(circle2))
    println(circle1.area)
  }

}

class Point (val x:Double,val y:Double)
{
  def distance(other:Point): Double =
  {
      math.sqrt((this.x-other.x)*(this.x-other.x)+(this.y-other.y)*(this.y-other.y))
  }
}

class Circle(override val x:Double,override val y:Double,val r :Double) extends Point(x,y)
{
  override def toString: String = s"x:$x,y:$y,r:$r"
  def area: Double = math.Pi*r*r
}
