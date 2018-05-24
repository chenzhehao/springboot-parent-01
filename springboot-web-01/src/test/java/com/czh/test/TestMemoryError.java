package com.czh.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Title: TestMemoryError.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: www.chenzhehao.com
 * </p>
 * 
 * @author chenzhehao
 * @date 2018年5月10日
 * @version 1.0
 */
public class TestMemoryError {

	private int count = 0;

	public void testFun() {
		count++;
		System.out.println(count);
		testFun();
	}

	public static void main(String[] args) {
		//stackOverflowError 栈溢出
//		TestMemoryError t =  new TestMemoryError();
//		t.testFun();
		
		//heap 堆溢出
//		List<byte[]> buffer = new ArrayList<byte[]>();    
//        buffer.add(new byte[10*1024*1024*24]);    
        
        Set set = new HashSet();
        set.add("1");
        set.add("2");
        set.add("1");
        System.out.println(set);
        
        
        class A{
        	private String a;
        	public A(String a){
        		this.a = a;
        	}
        	@Override
        	public boolean equals(Object a0){
        		if(a0 instanceof A){
        			return ((A) a0).a.equals(this.a);
        		}
        		return false;
        	}
        	
        }
        A a1 = new A("1");
        A a2 = a1;
        System.out.println(a1.equals(a2));
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());
        
        set.add(a1);
        set.add(a2);
        System.out.println(set);
        
        long i = -8;
        System.out.println(i >> 2);
        System.out.println(i >>> 2);
        System.out.println(i << 2);
        
        int n = 6;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        
        Map map = new HashMap(3);
        System.out.println(map.size());
        for(int j =0 ;j <49;j++){
        	map.put(j, j);
        }
        
        List list1 = new ArrayList();
        List list = new ArrayList();
        System.out.println(list.size());
        for(int j =0 ;j <49;j++){
        	list.add(j);
        }
        System.out.println(list);
        
        System.out.println(0x7fffffff);
        
        
        B b1 = new B();
        B b2 = new B();
        b1.b = 2;
        System.out.println("b1-a:"+b1.a);
		System.out.println("b1-b:"+b1.b);
		System.out.println("b2-a:"+b2.a);
		System.out.println("b2-b:"+b2.b);
	}
}

//final static
final class B{
	public final double a = Math.random();
	public static double b = Math.random();
	public B(){
		System.out.println("a:"+a);
		System.out.println("b:"+b);
	}
}