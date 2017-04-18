package com.java.thread.designPattern2fromThread.Strategy;

public class SubStrategy implements CalcStrategy {

	@Override
	public int calc(int x, int y) {
		// TODO Auto-generated method stub
		return x-y;
	}

}
