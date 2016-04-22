package com.java.collection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ArrayList
 * @author lxh
 * ArrayList是List接口的可变数组的实现。
 * 还提供了一些方法 操作内部用来存储列表的数组的大小。
 * 每个ArrayList实例都有一个容量，该容量是指用来存储列表元素的数组的大小。
 * 它总是至少等于列表的大小。
 * 
 * 随着元素添加，容量会自动增长。 在添加之前 可以用ensureCapacity操作来增加容量。
 * 这可以减少递增式再分配的数量。
 * 
 * 
 * 注意：此实现不是同步的。
 * 
 * 
 * 
 * 
 */

public class TestArrayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();

	}
	
	
	//参数
	
	/**
	 * 底层使用数组实现
	 */
	transient Object[] elementData; // non-private to simplify nested class access
	
	/**
     * 默认初始化容量为10
     */
    private static final int DEFAULT_CAPACITY = 10;
	
    
   //构造器
    
    /**
     * 1.构造一个空的ArrayList。
     * 2.构造一个固定容量的ArrayList。
     * 3.传入一个集合。
     */
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 容量调整
     */
    
    
    /**
     * 把调整到 ArrayList的当前size.
     * Trims the capacity of this <tt>ArrayList</tt> instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an <tt>ArrayList</tt> instance.
     */
 /*   public void trimToSize() {
  		//操作次数+1
        modCount++;
        //如果当前的size比ArrayList的长度小,则进行容量调整
        if (size < elementData.length) {
            elementData = (size == 0)
              ? EMPTY_ELEMENTDATA
              //调用Arrays的copyOf方法,调整容量
              : Arrays.copyOf(elementData, size);
        }
    }*/
    
    
    
	/**
	 * 不是同步的。
	 */

}
