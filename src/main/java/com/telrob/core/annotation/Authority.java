package com.telrob.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Authority {
	//所有权限，默认是这个值
	public static int PUBLIC=0;
	//超级管理员
	public static int SUPADMIN=1;
	//普通管理员
	public static int ADMIN=2;
	//一般用户
	public static int USER=3;
	int value() default PUBLIC;
}
