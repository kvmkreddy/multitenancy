package org.os.javaee.orm.multitenancy.hibernate.interceptor;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.os.javaee.orm.multitenancy.annotations.InjectTenantInfo;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;
import org.os.javaee.orm.multitenancy.context.ITenantContextHolder;
import org.os.javaee.orm.multitenancy.context.injector.ITenantContextInfoInjector;
import org.os.javaee.orm.multitenancy.validate.Assert;

/**
 * <p>Title: HibernateMTInterceptor</p>
 * <p><b>Description:</b> HibernateMTInterceptor</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * 
 * TODO This intercepter should be generic. Should be injected based on ORM provider(depending on configurations from spring).
 * 
 * TODO Inject both CLASS(i.e <code>org.os.javaee.orm.multitenancy.annotations.EnableMultiTenancy</code>) 
 * 		and METHOD (i.e <code>org.os.javaee.orm.multitenancy.annotations.InjectTenantInfo</code>) level annotation classes as <code>java.lang.Class</code> references
 * 
 * @author Murali Reddy
 * @version 1.0
 */
public class HibernateMTInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 5132099610022438699L;
	private static final Logger log = Logger.getLogger(HibernateMTInterceptor.class);
	
	private transient ITenantContextHolder contextHolder;
	private ITenantContextInfoInjector contextInjector;
	
	/**
	 * @return the contextHolder
	 */
	public ITenantContextHolder getContextHolder() {
		return contextHolder;
	}


	/**
	 * @param contextHolder the contextHolder to set
	 */
	public void setContextHolder(ITenantContextHolder contextHolder) {
		this.contextHolder = contextHolder;
	}
	
	
	/**
	 * @return the contextInjector
	 */
	public ITenantContextInfoInjector getContextInjector() {
		return contextInjector;
	}


	/**
	 * @param contextInjector the contextInjector to set
	 */
	public void setContextInjector(ITenantContextInfoInjector contextInjector) {
		this.contextInjector = contextInjector;
	}


	/* (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#preFlush(java.util.Iterator)
	 */
	@Override
	public void preFlush(@SuppressWarnings("rawtypes") Iterator entities) {
		//TODO Add better logging facilities.
		log.debug("*************************** Call in prePlush(). *************************** ");
		if(Assert.isNotNull(entities,contextHolder,contextHolder.getTenantContext())){
			while(entities.hasNext()){
				Object entity = entities.next();
				log.debug("Call in onDelete() -->:"+(entity.getClass().getCanonicalName()));
				invokeMethod(entity);
			}
		}
	}
	
	protected void invokeMethod(Object clazz) {
		if(clazz.getClass().isAnnotationPresent(MultiTenancy.class) && 
				(clazz.getClass().getAnnotation(MultiTenancy.class) != null)&&
				(((MultiTenancy)clazz.getClass().getAnnotation(MultiTenancy.class)).strategy() == Strategy.DISCRIMINATOR)){
			Method[] allMethods = clazz.getClass().getDeclaredMethods();
			for(Method method:allMethods){
				if(method.isAnnotationPresent(InjectTenantInfo.class)){
					try {
						this.getContextInjector().inject(contextHolder.getTenantContext(), clazz, method);
					} catch (IllegalArgumentException | SecurityException e) {
						log.error("Exception in injecting context info. Exception message -->:"+(e.getMessage()),e);
					}
				}
			}
		}
	}
	
	
/*	*//**
	 * @param clazz
	 *//*
	protected void invokeMethod(Object clazz) {
		if(clazz.getClass().isAnnotationPresent(MultiTenancy.class) && 
				(clazz.getClass().getAnnotation(MultiTenancy.class) != null)&&
				(((MultiTenancy)clazz.getClass().getAnnotation(MultiTenancy.class)).strategy() == Strategy.DISCRIMINATOR)){
			Method[] allMethods = clazz.getClass().getDeclaredMethods();
			for(Method method:allMethods){
				if(method.isAnnotationPresent(InjectTenantInfo.class)){
					try {
						String tenantInfo = contextHolder.getTenantContext().getTenantInfo().toString();
						if(method.getParameterTypes()[0].isPrimitive()){
							switch(method.getParameterTypes()[0].getName()){
							case "int":
								method.invoke(clazz, Integer.valueOf(tenantInfo).intValue());
								break;
							case "long":
								method.invoke(clazz, Long.valueOf(tenantInfo).longValue());
								break;
							case "double":
								method.invoke(clazz, Double.valueOf(tenantInfo).doubleValue());
								break;
							}
						}else if(method.getParameterTypes()[0].getCanonicalName().startsWith("java.lang.")){
							switch(method.getParameterTypes()[0].getCanonicalName()){
							case "java.lang.String":
								method.invoke(clazz, tenantInfo);
								break;
							case "java.lang.Integer":
								method.invoke(clazz, Integer.valueOf(tenantInfo));
								break;
							case "java.lang.Long":
								method.invoke(clazz, Long.valueOf(tenantInfo));
								break;
							case "java.lang.Double":
								method.invoke(clazz, Double.valueOf(tenantInfo));
								break;
							default:
								method.invoke(clazz, tenantInfo);
								break;									
							}
						}else if(method.getParameterTypes()[0].isAnnotationPresent(MultiTenancy.class) &&
								(method.getParameterTypes()[0].getAnnotation(MultiTenancy.class) != null)&&
								(((MultiTenancy)method.getParameterTypes()[0].getAnnotation(MultiTenancy.class)).strategy() == Strategy.DISCRIMINATOR)){
							//method.invoke(entity, new MultiTenancyInfo(new Integer(contextHolder.getTenantContext().getTenantId())));
							//TODO Below logic of creating method argument object (by using reflection) is wrong. We should inject the 'composite MT Key' creator/builder into this interceptor.
							Thread.currentThread().getContextClassLoader().loadClass(method.getParameterTypes()[0].getClass().getCanonicalName());
							invokeMethod(method.getParameterTypes()[0].getClass().getConstructor().newInstance());
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
								ClassNotFoundException | InstantiationException | NoSuchMethodException | SecurityException e) {
							//TODO Add better error handling facility here.
						e.printStackTrace();
					}
				}
			}
		}
	}*/
}