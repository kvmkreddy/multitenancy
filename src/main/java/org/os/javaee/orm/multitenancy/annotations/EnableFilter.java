package org.os.javaee.orm.multitenancy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: EnableFilter</p>
 * <p><b>Description:</b> EnableFilter</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * TODO How to create these filter definitions at runtime.?
 * @author Murali Reddy
 * @version 1.0
 */
@Target(value=ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableFilter {

	public String[] filterNames() default {};
	public Filters filters() default @Filters;
}
