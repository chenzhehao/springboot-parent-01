package com.czh.test;

/**
 * <p>
 * Title: TestInnerClass.java
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
public class TestInnerClass {

	private static int outParamStatic = 0;
	int outParam = 0;

	private void getOutParam() {
		System.out.println("	outParam");
	}

	private static void getOutParam2() {
		System.out.println("	outParam");
	}

	// 内部类（可以又private、protected、public、default等访问权限修饰），可以访问外部类中的所有成员及方法
	private class InnerClass {
		public String inParam = "inParam";
		public static final String inParamStatic = "inParamStatic";// 必须被final修饰才能使用static

		public void getParam() {
			System.out.println("非静态内部类：");
			System.out.println("	outParamStatic：" + outParamStatic);
			System.out.println("	outParam2：" + outParam);
			System.out.println("	inParam：" + inParam);
			System.out.println("	inParamStatic：" + inParamStatic);
			getOutParam();
			getOutParam2();
		}
	}

	// 静态内部类，内部类的成员都被显示的声明为静态
	protected static class InnerClass2 {
		private String inParam2 = "inParam2";
		private static String inParamStatic2 = "inParamStatic2";// 能使用static

		public void getParam2() {
			System.out.println("静态内部类：");
			System.out.println("	outParamStatic：" + outParamStatic);
			// System.out.println(" outParam2：" + outParam);//非静态不能调用
			System.out.println("	inParam2：" + inParam2);
			System.out.println("	inParamStatic2：" + inParamStatic2);
			// getOutParam();//非静态不能调用
			getOutParam2();
		}
	}

	public void getParam3() {
		// 局部内部类
		class InnerClass3 {
			private String inParam3 = "inParam3";
			private static final String inParamStatic3 = "inParamStatic3";// 必须被final修饰才能使用static
			InnerClass c = new InnerClass();
			InnerClass2 c2 = new InnerClass2();

			void getSum() {
				System.out.println("局部内部类：");
				System.out.println("	outParamStatic：" + outParamStatic);
				System.out.println("	outParam：" + outParam);// 非静态不能调用
				System.out.println("	inParam3：" + inParam3);
				System.out.println("	inParamStatic3：" + inParamStatic3);
				System.out.println("	inParam：" + c.inParam);
				System.out.println("	inParamStatic：" + c.inParamStatic);
				System.out.println("	inParam2：" + c2.inParam2);
				System.out.println("	inParamStatic2：" + c2.inParamStatic2);
				getOutParam();// 非静态不能调用
				getOutParam2();
			}
		}
		InnerClass3 c3 = new InnerClass3();
		c3.getSum();
	}

	public void getParam4(final int i) {
		int funParam = 1;
		// 匿名内部类
		ParentClass p = new ParentClass() {
			private String inParam4 = "inParam4";
			private static final String inParamStatic4 = "inParamStatic4";// 必须被final修饰才能使用static

			void getNum() {
				System.out.println("匿名内部类：");
				System.out.println("	outParamStatic：" + outParamStatic);
				System.out.println("	outParam：" + outParam);// 非静态不能调用
				System.out.println("	inParam4：" + inParam4);
				System.out.println("	inParamStatic4：" + inParamStatic4);
				System.out.println("	funParam：" + funParam);
				System.out.println("	i：" + i);
			}
		};
		p.getNum();
	}

	public static void main(String[] args) {

		TestInnerClass outClass = new TestInnerClass();
		InnerClass inClass = outClass.new InnerClass();
		inClass.getParam();
		InnerClass2 inClass2 = new InnerClass2();
		inClass2.getParam2();
		outClass.getParam3();
		outClass.getParam4(3);

		ParentClass sun = new SunClass();
		sun.getParent();
		System.out.println(sun instanceof SunClass);
		System.out.println(((SunClass) sun).getSun());

		ParentClass parent = new ParentClass();
		// System.out.println(((SunClass)parent).getSun());
	}
}
