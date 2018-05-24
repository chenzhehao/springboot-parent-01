package com.czh.springboot.service;

public interface ITestService {

	public static final String a = "1";
	public String b = "1";

	public String test();

	public String testLog();

	public String getUser();

	public String setUser(int id);

	public String lockUser(int id);

	public static String getUser2() {
		return "b";
	}

	public default String getUser1() {
		return "a";
	}

}
