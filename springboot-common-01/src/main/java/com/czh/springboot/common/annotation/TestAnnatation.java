package com.czh.springboot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * <p>
 * Title: TestAnnatation.java
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
 * @date 2018年5月22日
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = { TestAnnatation.TestAnnatationValidator.class })
public @interface TestAnnatation {
	String message() default "测试annatation";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String testArg() default "1";

	String testArg2() default "2";

	int testArg3() default 3;

	public class TestAnnatationValidator implements ConstraintValidator<TestAnnatation, Object> {

		public String s;

		@Override
		public void initialize(TestAnnatation parameters) {
			System.out.println(parameters);//此处可以对注解中配置的参数做处理--每个对象使用的该注解该init方法只会执行一次，后续从缓存中取
			s = parameters.testArg() + parameters.testArg2() + parameters.testArg3();
		}

		@Override
		public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
			System.out.println(object);//object为使用注解的对象的值
			System.out.println(s);
			return object != null;
		}
	}
}
