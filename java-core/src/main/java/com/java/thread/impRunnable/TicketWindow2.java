package com.java.thread.impRunnable;

/**
 * 创建线程的另一种方法是声明实现 Runnable 接口的类。
 * 该类然后实现 run 方法。
 * 然后可以分配该类的实例，在创建 Thread 时作为一个参数来传递并启动
 * @author lxh
 *
 */
public class TicketWindow2 implements Runnable {
	
	/**
	 * 此处没有用到静态 就实现 了我们要的效果。
	 */
	private int max_value = 0;

	public void run() {
		while (true) {
			if (max_value > 50)
				break;
			System.out.println(Thread.currentThread().getName() + ":" + max_value++);
		}
	}
}