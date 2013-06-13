package org.os.javaee.orm.multitenancy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: MultiTenancy</p>
 * <p><b>Description:</b> MultiTenancy</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@Target(value={ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiTenancy {

	public enum Strategy{
		DATABASE,SCHEMA,DISCRIMINATOR,RUNTIME,NONE
	}
	
	public Strategy strategy() default Strategy.NONE;
}
