package com.czh.springboot.service.impl.thread;
/**  
* <p>Title: MapTest.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年5月8日  
* @version 1.0  
*/

import java.util.HashMap;
import java.util.Map;

public class MapTest<T, S> {

	private volatile Map<T, S> map;

	public MapTest() {
		map = new HashMap<T, S>();
	}

	public synchronized void put(T t, S s) {
		map.put(t, s);
	}

	public synchronized S get(T t) {
		S s = map.get(t);
		this.put(t, (S) "b");
		return s;
	}

	public int size() {
		return map.size();
	}
}
