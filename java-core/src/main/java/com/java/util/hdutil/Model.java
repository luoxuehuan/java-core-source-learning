package com.java.util.hdutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体模型类注解，用来通过模式和表名得到全表名
 * 
 * @author sunfeng
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Model {

	/**
	 * 表名
	 * 
	 * @return 表名
	 */
	public String name() default "";

	/**
	 * 模式名
	 * 
	 * @return 模式名
	 */
	public String schema() default "";

}
