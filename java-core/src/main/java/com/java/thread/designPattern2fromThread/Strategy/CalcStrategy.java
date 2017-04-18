package com.java.thread.designPattern2fromThread.Strategy;

/**
* 策略接口，主要是规范或者让结构程序知道如何进行调用
*/
public interface CalcStrategy {
	int calc(int x,int y);
}
