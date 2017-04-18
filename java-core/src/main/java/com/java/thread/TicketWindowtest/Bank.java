package com.java.thread.TicketWindowtest;

public class Bank {
	public static void main(String[] args) {
		TicketWindow t1 = new TicketWindow();
		TicketWindow t2 = new TicketWindow();
		TicketWindow t3 = new TicketWindow();
		
		t1.start();
		t2.start();
		t3.start();
		
		/**
		 * 直接运行的话，叫出的号可能会是同一张
		 * 
		 * 变量改成static 又不合理。
		 * 
		 * 那可不可以线程t1 start 三次呢？不可以！涉及到：线程状态的改变。
		 */
	}
}