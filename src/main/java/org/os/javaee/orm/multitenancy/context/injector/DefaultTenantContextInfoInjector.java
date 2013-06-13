package org.os.javaee.orm.multitenancy.context.injector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;
import org.os.javaee.orm.multitenancy.context.ITenantContext;

/**
 * <p>Title: DefaultTenantContextInfoInjector</p>
 * <p><b>Description:</b> DefaultTenantContextInfoInjector</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@ReferenceImplementation
public class DefaultTenantContextInfoInjector implements ITenantContextInfoInjector {

	private static final Logger log = Logger.getLogger(DefaultTenantContextInfoInjector.class);
	
	/* (non-Javadoc)
	 * @see org.os.javaee.orm.multitenancy.context.injector.ITenantContextInfoInjector#inject(org.os.javaee.orm.multitenancy.context.ITenantContext, java.lang.Object)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public void inject(ITenantContext<?> context, Object entity, Method method) {
		invokeMethod((ITenantContext<String>) context,entity,method);
		
	}

	/**
	 * @param context
	 * @param entity
	 */
	@SuppressWarnings({ "rawtypes"})
	protected void invokeMethod(ITenantContext<String> context, Object entity, Method method) {
		try {
			String tenantInfo = (String) context.getTenantInfo();
			if (method.getParameterTypes()[0].isPrimitive()) {
				switch (method.getParameterTypes()[0].getName()) {
				case "int":
					method.invoke(entity, Integer.valueOf(tenantInfo).intValue());
					break;
				case "long":
					method.invoke(entity, Long.valueOf(tenantInfo).longValue());
					break;
				case "double":
					method.invoke(entity, Double.valueOf(tenantInfo).doubleValue());
					break;
				}
			} else if (method.getParameterTypes()[0].getCanonicalName().startsWith("java.lang.")) {
				switch (method.getParameterTypes()[0].getCanonicalName()) {
				case "java.lang.String":
					method.invoke(entity, tenantInfo);
					break;
				case "java.lang.Integer":
					method.invoke(entity, Integer.valueOf(tenantInfo));
					break;
				case "java.lang.Long":
					method.invoke(entity, Long.valueOf(tenantInfo));
					break;
				case "java.lang.Double":
					method.invoke(entity, Double.valueOf(tenantInfo));
					break;
				default:
					method.invoke(entity, tenantInfo);
					break;
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			log.error("Exception in injecting context info. Exception message -->:"+(e.getMessage()),e);
		}
	}
}