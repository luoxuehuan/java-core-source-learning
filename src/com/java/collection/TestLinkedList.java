package com.java.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List a = new LinkedList();
		List b = new CopyOnWriteArrayList();
		b.add("a");
		b.add("b");
		b.add("c");
		b.add("d");
		b.add("e");
		b.add("f");
		for(int i = 0; i < b.size(); i++)  
        {  
			System.out.println(i);
            b.get(i);  
            System.out.println(b.get(i)); 
            /*b.remove("d");*/
           b.remove(4);
        }  
		
		/*Iterator a1 = b.iterator();
		while(a1.hasNext()){
			System.out.println(a1.next());
		}*/
		
		
	}

}
