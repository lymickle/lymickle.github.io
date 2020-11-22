package com.atguigu.day3;

import java.io.Serializable;

public class Person implements Serializable,Cloneable {
 int age;
 String name;

 public int getAge() {
  return age;
 }

 public void setAge(int age) {
  this.age = age;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public Person() {
  System.out.println("无参构造执行了");
 }

 public Person(String name,int age) {
  this.age = age;
  this.name = name;
 }

 public void eat()
 {
  System.out.println(name+" is eating !");
 }

 public void eat(String food)
 {
        System.out.println(name+" is eating "+food);
 }

 @Override
 public String toString() {
  return "Person{" +
          "age=" + age +
          ", name='" + name + '\'' +
          '}';
 }
 private void privateMethod()
 {
  System.out.println("This is a private method");
 }
 public static void staticMethod()
 {
  System.out.println("This is a static method");
 }
}
