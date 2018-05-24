package com.czh.test.transienttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>
 * Title: TransientTest.java
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
 * @date 2018年5月9日
 * @version 1.0
 */
public class TransientTest {
	public static void main(String[] args) {

		UserBo userBo = new UserBo();
		userBo.setName("Alexia");
		userBo.setAge(28);
		userBo.setPassword("123456");

		System.out.println("read before Serializable: ");
		System.out.println("Name: " + userBo.getName());
		System.out.println("Age: " + userBo.getAge());
		System.out.println("Password: " + userBo.getPassword());
		System.out.println("address: " + userBo.getAddress());
		userBo.address = "河南";
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/Users/chenzhehao/Desktop/UserBo.txt"));
			os.writeObject(userBo); // 将User对象写进文件
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("/Users/chenzhehao/Desktop/UserBo.txt"));
			UserBo userBo1 = (UserBo) is.readObject(); // 从流中读取User的数据
			is.close();

			System.out.println("\nread after Serializable: ");
			System.out.println("Name: " + userBo1.getName());
			System.out.println("Age: " + userBo1.getAge());
			System.out.println("password: " + userBo1.getPassword());
			System.out.println("address: " + userBo1.address);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
