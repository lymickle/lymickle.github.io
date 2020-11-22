package com.atguigu.day2

import scala.collection.mutable

object foldleftTest {
  def main(args: Array[String]): Unit = {
    val list1 = List("hello world","hello hello","atguigu atguigu world")
    val words = list1.flatMap(_.split(" "))
    print(words)
    val map:mutable.Map[String,Int]=words.foldLeft(mutable.Map[String,Int]())(
      (map,word)=>{
        val value =map.getOrElse(word,0)+1
        map += (word->value)
        map
      })
    print(map)

    val list2 =List(30,50,70,60,10,20)
    val list3 = list2.scanLeft(100)(_+_)
    println(list3)
    println(list2.zipWithIndex)
    println(list2.zipWithIndex.filter(t=>t._2%2==1).map(_._2))

    val t =List((30,1),(20,2),(10,3))
    print(t.unzip)

    val it = "abcdefg".toIterator.toList
    it.foreach(println(_))
    println("-------------")
    it.foreach(println(_))

    val list4 =List(30,50,7,6,1,20)

    var booleanToInts = list4.groupBy(_ % 2  )
    println(booleanToInts)

    val functionToMap = words.groupBy(word=>word).map(kv=>(kv._1,kv._2.size)).toList
    println(functionToMap)

    val list5 = List("hello"->2,"hello"->3,"atguigu"->1,"hello"->1)
    val stringToTuples = list5.groupBy(_._1)
    println(stringToTuples)

   val temp =  stringToTuples.map(kv=>
    {
      val key = kv._1
      val value = kv._2.map(_._2)
      key->value
    }

    )

   val result =  temp.reduce((_._2))

    println(result)





  }
}
