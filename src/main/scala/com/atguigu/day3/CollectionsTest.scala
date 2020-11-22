package com.atguigu.day3

import scala.collection.mutable

object CollectionsTest {
  def main(args: Array[String]): Unit = {
    val qu :mutable.Queue[Int] = mutable.Queue(10,20,30)
    qu.enqueue(100)
    print(qu)
    qu.dequeue();
    println(qu)
    val sta :mutable.Stack[Int] = mutable.Stack(10,20,30)
    sta.push(100)
    print(sta)
    sta.pop()
    println(sta)

    val map = mutable.Map("a"->1,"b"->2,"c"->3)
    println(map("a"))
    println(map.getOrElse("c",102))
    println( System.identityHashCode(map))
    for (elem <- map) {
      println(elem._1,elem._2)
    }
    for((k,3)<- map) {
      {
        println(k)
      }
    }
    map += "d"->10
    println( System.identityHashCode(map))

    var set1 = Set(11,13,30,10)
    println(set1)

    set1 += 100


    set1 ++= Array(11,13,20,30,40)
    println("set1:"+set1)
    println(toDuplicate(List(11,20,20,30,10,20)))

    val set2 = set1.map(x=>(x*x,x*x*x))
    println(set2)
    println(set1.flatMap(x=>Array(x,x*x,x*x*x)))

    val list1 = List("hello world","atguigu hello","hello hello")
    val list2 = list1.map(x=>x.split(" ").toList)
    println(list2)
    println("abcDEfG".flatMap( x=> if (x.isLower) List(x,x.toUpper)
    else List(x,x.toLower)))

    print("set1:"+set1)
    val set3 = set1.flatMap(x=> if(x%2==0) List(x) else List())
    print(set3)

    val set4 = set1.filter(x=>x%2==0)
    println(set4)

    val list = List(null, 11, 20, 20, 30, 10, 20, "adb")

    val fil=list.filter(_.isInstanceOf[Int]).map(_.asInstanceOf[Int]+1)
    print(fil)
    val list4 = List( 11, 20, 20, 30, 10, 20)
    val result = list4.reduce((x,y)=>x+y)
   println(result)
    println(list4.foldLeft("")(_+_))

  }

  def toDuplicate(list:List[Int]): List[Int] =
  {
     list.toSet.toList
  }
}
