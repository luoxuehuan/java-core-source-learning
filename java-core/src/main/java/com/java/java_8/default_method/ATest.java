package com.java.java_8.default_method;

public class ATest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AImpl a = new AImpl();
        a.say(333);
        a.run();
    }

}


/**
 * Java 1.8 可以用默认方法了。
 */
interface A {
    default void say(int a) {
        System.out.println(a);
    }
}

interface B {
    default void run() {
        System.out.println("A.run");
    }

}

class AImpl implements A, B {


    public void run() {
        System.out.println("AImpl.run");
    }
}