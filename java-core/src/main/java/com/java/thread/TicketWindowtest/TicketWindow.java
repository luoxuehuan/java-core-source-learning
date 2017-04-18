package com.java.thread.TicketWindowtest;

public class TicketWindow extends Thread {
	
	/**
	 * 为什么不用static,因为static的生命周期太长了！
	 */
	int max_value = 0;// 最大的号码

	@Override
	public void run() {
		while (true) {
			if (max_value > 50)

			{
				break;
			}
			System.out.println(currentThread().getName() + ":" + max_value++);
		}
	}
}
