package org.os.javaee.orm.multitenancy.hibernate.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
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
	
	/* (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#onLoad(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		log.debug("*************************** Call in onLoad(). *************************** ");
		return super.onLoad(entity, id, state, propertyNames, types);
	}


	/* (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#onPrepareStatement(java.lang.String)
	 */
	@Override
	public String onPrepareStatement(String sql) {
		// TODO Auto-generated method stub
		log.debug("*************************** Call in onPreparedStatement(). *************************** "+(sql));
		return super.onPrepareStatement(sql);
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
}