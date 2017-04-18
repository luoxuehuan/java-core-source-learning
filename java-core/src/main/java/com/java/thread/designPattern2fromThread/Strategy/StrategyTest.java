package com.java.thread.designPattern2fromThread.Strategy;

public class StrategyTest {
	public static void main(String[] args) {
		
		// 没有任何策略时的结果
		Calculator c = new Calculator(30, 24);
		System.out.println(c.result());
		
		// 传入减法策略的结果
		Calculator c1 = new Calculator(10, 30, new SubStrategy());
		System.out.println(c1.result());
		
		// 看到这里就可以看到策略模式强大了，算法可以随意设置，系统的结构并不会发生任何变化
		Calculator c2 = new Calculator(30, 40, new CalcStrategy() {
			public int calc(int x, int y) {
				return ((x + 10) - (y * 3)) / 2;
			}
		});
		System.out.println(c2.result());
	}
}
