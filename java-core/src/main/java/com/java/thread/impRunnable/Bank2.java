package com.java.thread.impRunnable;


/**
 *  Thread 是骨架，是提供功能的，而 Runnable 只是其中某个业务逻辑的一种实现罢了
 *  
 *  因为业务逻辑会是很复杂，也会是千变万化的，因此我们需要对
它进行高度的抽象，这样才能将具体业务逻辑与抽象分离，程序的可扩展性才能够强
 * @author lxh
 *
 */
public class Bank2 {
	public static void main(String[] args)
	{
		TicketWindow2 tw2 = new TicketWindow2();//1
		
		
		/**
		 * 在创建 Thread 时作为一个参数来传递并启动
		 */
		Thread t1 = new Thread(tw2);//2
		Thread t2 = new Thread(tw2);//3
		Thread t3 = new Thread(tw2);//4
		Thread t4 = new Thread(tw2);//2
		Thread t5 = new Thread(tw2);//3
		Thread t6 = new Thread(tw2);//4
		Thread t7 = new Thread(tw2);//2
		Thread t8 = new Thread(tw2);//3
		Thread t9 = new Thread(tw2);//4
		
		t1.start();//5
		t2.start();//6
		t3.start();//7
		t4.start();//5
		t5.start();//6
		t6.start();//7
		t7.start();//5
		t8.start();//6
		t9.start();//7
	}
}