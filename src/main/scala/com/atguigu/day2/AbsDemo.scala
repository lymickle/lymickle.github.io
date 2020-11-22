package com.atguigu.day2

object AbsDemo {
  def main(args: Array[String]): Unit = {
      val p1:Person = new Person("",10)
    p1.say(1)

  }
}


abstract class Human (val name:String)
{
  val age:Int
  var sex:String
  val a :Int
  def say(a:Int)
}

class Person(override  val name:String,override val age:Int) extends Human(name)
{
  override var sex: String = _
  override val a: Int = 0

  override def say(a: Int): Unit =
    {
      println("hello")
    }
}