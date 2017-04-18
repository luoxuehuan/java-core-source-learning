package com.java.thread.designPattern2fromThread.Template;

public abstract class Diagram {

	protected char c;

	public Diagram(char c) {
		this.c = c;
	}

	abstract protected void print(int size);

	abstract protected void printContent(String msg);

	public final void display(String msg) {
		int len = msg.getBytes().length;
		print(len);
		printContent(msg);
		print(len);
	}
}