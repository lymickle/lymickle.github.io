package com.atguigu.day3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestPerson {
    public static void main(String[] args) throws Exception {
     reflectOpe1();
    }
    public static  void reflectOpe1() throws Exception
    {
        Class<?> class1 = Class.forName("com.atguigu.day3.Person");
        System.out.println(class1.getName());
        System.out.println(class1.getPackage());
        System.out.println(class1.getSuperclass().getName());
        Class<?>[] interfaces = class1.getInterfaces();
        System.out.println(Arrays.toString(interfaces));
        System.out.println(class1.getSimpleName());
        System.out.println(class1.getTypeName());
        Constructor<?>[] constructors = class1.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.toString());
        }

        Constructor<?> constructor = class1.getConstructor();
        Person person =(Person) constructor.newInstance();
        System.out.println(person.toString());
        Person wangwu = (Person) class1.newInstance();
        System.out.println(wangwu.toString());
        Constructor<?> constructor1 = class1.getConstructor(String.class, int.class);
        Person xiaoli = (Person) constructor1.newInstance("xiaoli", 20);
        System.out.println(xiaoli.toString());

        Method[] methods = class1.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("---------------------");
        Method[] declaredMethods = class1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        Method method = class1.getMethod("eat");
        Person zhangsan = (Person) class1.newInstance( );
        method.invoke(zhangsan);
        System.out.println("---------------------");
        Method toStringMethod = class1.getMethod("toString");
        Object result = toStringMethod.invoke(zhangsan);
        System.out.println(result);
        System.out.println("---------------------");
        Method eating = class1.getMethod("eat",String.class);
        Object hotdog = eating.invoke(zhangsan, "hotdog");

        Method privateMethod = class1.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(zhangsan);

        Method staticMethod = class1.getMethod("staticMethod");
        staticMethod.invoke(null);


    }



}
