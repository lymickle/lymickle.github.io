package com.atguigu.day3;


public class My4 {
    public static void main(String[] args) {

         B b = new B();
         A a = b;
        System.out.println(b.a);
        System.out.println(a.a);
        System.out.println(b.getA());
        System.out.println(a.getA());
    }
}

class A
{
    public int a = 10;
    public int getA()
    {
        return a;
    }
}

class B extends A
{
    public int a = 100;
}
