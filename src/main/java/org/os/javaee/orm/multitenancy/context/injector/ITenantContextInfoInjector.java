package org.os.javaee.orm.multitenancy.context.injector;

import java.lang.reflect.Method;

import org.os.javaee.orm.multitenancy.context.ITenantContext;


/**
 * <p>Title: ITenantContextInfoInjector</p>
 * <p><b>Description:</b> ITenantContextInfoInjector</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public interface ITenantContextInfoInjector{

	public void inject(ITenantContext<?> context, Object entity, Method method);
}
