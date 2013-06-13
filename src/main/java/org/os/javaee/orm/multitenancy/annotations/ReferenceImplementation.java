package org.os.javaee.orm.multitenancy.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: ReferenceImplementation</p>
 * <p><b>Description:</b> Indicated the implementation is only for reference.</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@Target(value={ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReferenceImplementation{

	public String description() default "";
}
