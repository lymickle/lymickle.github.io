package com.atguigu.day2

import scala.:+
import scala.collection.mutable.ArrayBuffer

object TypeDemo {
  def main(args: Array[String]): Unit = {
    val d:C = new D()

    val result = d.isInstanceOf[C]

    println(result)
    d.asInstanceOf[D].foo()

    type E = C
    val e = new E
    println(e.isInstanceOf[E])
    println(e.isInstanceOf[C])
    println(e.getClass.getSimpleName)

    val buffer =ArrayBuffer[Int](10,20,30)
    val buffer1 =ArrayBuffer[Int](300,200,100)
    val buffer2 = buffer1 ++: buffer

    for (elem <- buffer2 if elem!=20) {
      println(elem)
    }

    println("-----------------")
    println(buffer2.head)
    println(buffer2.last)
    println(buffer2.tail)
    println(buffer2.take(2))

    val array:Array[Array[Int]] = Array.ofDim(3,2)

    var array1 = new Array[Int](10)
    println("------------------")
    for (elem <- array1) {
      println(elem)
    }
    println("------------------")
    array1:+= 10
    println("------------------")
    for (elem <- array1) {
      println(elem)
    }
    println("------------------")
    val array2 = new ArrayBuffer[Int]();
    array2.append(10)
    array2 +=20
    print(array2)
    array2.insert(0,-1,-2,100,100)
    array2.remove(0,2)
    print(array2)
    array2 -= 100
    print(array2)
  }
}


class C
class D extends C
{
  def foo(): Unit =
  {
    println("foo....")
  }
}
