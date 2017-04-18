package com.java.thread.extendsthread;

public class DemoTest1 {
	
		public static void main(String[] args) {
			ThreadDemo t1 = new ThreadDemo("t1");
			ThreadDemo t2 = new ThreadDemo("t2", 200);
			t1.run();
			t2.run();
			
		/*	t1.start();
			t2.start();*/
		}
	}

