package com.java.thread.designPattern2fromThread.Strategy;

public class Calculator {
	private int x = 0;
	private int y = 0;
	private CalcStrategy strategy = null;

	public Calculator(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Calculator(int x, int y, CalcStrategy strategy) {
		this(x, y);
		this.strategy = strategy;
	}

	public int calc(int x, int y) {
		return x + y;
	}

	/**
	 * 只需关注接口，并且将接口用到的入参传递进去即可，并不关心到底具体是要如何进行业务封装
	 * 
	 * @return
	 */
	public int result() {
		if (null != strategy) {
			return strategy.calc(x, y);
		}
		return calc(x, y);
	}
}
