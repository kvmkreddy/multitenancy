package org.os.javaee.orm.multitenancy.context.injector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;
import org.os.javaee.orm.multitenancy.context.CompositeTenantInfo;
import org.os.javaee.orm.multitenancy.context.ITenantContext;
import org.os.javaee.orm.multitenancy.entity.MultiTenancyInfo;

/**
 * <p>Title: CompositeTenantContextInfoInjector</p>
 * <p><b>Description:</b> CompositeTenantContextInfoInjector</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@ReferenceImplementation
public class CompositeTenantContextInfoInjector implements ITenantContextInfoInjector{
	
	private static final Logger log = Logger.getLogger(CompositeTenantContextInfoInjector.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public void inject(ITenantContext<?> context, Object entity,Method method) {
		invokeMethod((ITenantContext<CompositeTenantInfo>) context,entity,method);
		
	}

	/**
	 * @param context
	 * @param entity
	 */
	@SuppressWarnings({ "rawtypes"})
	protected void invokeMethod(ITenantContext<CompositeTenantInfo> context, Object entity, Method method) {
		try {
			CompositeTenantInfo tenantInfo = (CompositeTenantInfo) context.getTenantInfo();
			method.invoke(entity, new MultiTenancyInfo(tenantInfo.getTenantIdOne(), tenantInfo.getTenantIdTwo(), tenantInfo.getTenantIdThree()));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			log.error("Exception in injecting context info. Exception message -->:"+(e.getMessage()),e);
		}
	}

}
