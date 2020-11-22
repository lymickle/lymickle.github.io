package com.atguigu.day2

object ExtendsDemo {
  def main(args: Array[String]): Unit = {
    val b = new B
    val a = b
    System.out.println(b.n)
    System.out.println(a.n)
  }
}

class A(val n: Int)
{
   def this()
     {this(10)}
}

class B extends A
{
  override val n: Int = 100
}
