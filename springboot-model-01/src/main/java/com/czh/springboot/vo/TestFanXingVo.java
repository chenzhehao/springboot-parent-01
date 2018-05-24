package com.czh.springboot.vo;

import java.lang.reflect.Method;

/**  
* <p>Title: TestFanXingVo.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月8日  
* @version 1.0  
*/
public class TestFanXingVo<T> {
	
	public TestFanXingVo(){
		
	}
	
	public <S> S initValue(S t){
		return t;
	}
	
	public Object getString(T t, String type, Object[] args){
		try {
			Class[] clazz = null;
			if(args != null){
				for (int i = 0; i < args.length; i++) {
					clazz[i] = args[i].getClass();
				}
			}
			Method method = t.getClass().getMethod(type, clazz);
			return (Object)method.invoke(t, args);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
}
