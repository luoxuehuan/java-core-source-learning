package com.java.java8test;

public class ATest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AImpl a = new AImpl();
		a.say(333);
		a.run();
	}

}

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

class AImpl implements A,B{


	public void run(){
		System.out.println("ddd");
	}
}