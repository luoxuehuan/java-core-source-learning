package com.java.util;

import java.text.DecimalFormat;

public class MathUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getPercent(0,6));
	}
	
	public static String getPercent(int x,int total){
		String result="";//接受百分比的值
		double x_double=x*1.0;
		double tempresult=x/(total*1.0);
		//NumberFormat nf   =   NumberFormat.getPercentInstance();     注释掉的也是一种方法
		//nf.setMinimumFractionDigits( 2 );        保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("0%");    //##.00%   百分比格式，后面不足2位的用0补齐
		//result=nf.format(tempresult);
		result= df1.format(tempresult);
		return result;
	}

}
