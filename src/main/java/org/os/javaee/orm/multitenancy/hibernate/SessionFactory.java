package org.os.javaee.orm.multitenancy.hibernate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.os.javaee.orm.multitenancy.annotations.EnableFilter;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;

/**
 * <p>Title: SessionFactory</p>
 * <p><b>Description:</b> SessionFactory</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class SessionFactory {

	private org.hibernate.SessionFactory  sessionFactory = null;
	@Deprecated
	private Map<String, List<String>> daoFilterMappings = new HashMap<String, List<String>>();
	
	private ThreadLocal<List<String>> filterConfig = new ThreadLocal<List<String>>();
	
	public SessionFactory() { }

	public SessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return
	 * @throws HibernateException
	 * @see org.hibernate.SessionFactory#getCurrentSession()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Session getCurrentSession(Class...classes) throws HibernateException {
		Session session = getSessionWithoutFilters();
		if(classes != null && classes.length==1 &&
				classes[0].isAnnotationPresent(MultiTenancy.class) &&
				(classes[0].getAnnotation(MultiTenancy.class) != null)&&
				(((MultiTenancy)classes[0].getAnnotation(MultiTenancy.class)).strategy() == Strategy.DISCRIMINATOR) &&
				classes[0].isAnnotationPresent(EnableFilter.class) && 
				classes[0].getAnnotation(EnableFilter.class) != null){
					String[] filterNames = ((EnableFilter)classes[0].getAnnotation(EnableFilter.class)).filterNames();
					if(filterNames != null && filterNames.length >0){
						this.filterConfig.set(Arrays.asList(filterNames));
						for(String filterName:filterNames){
							
							session.enableFilter(filterName);
						}
					}
		}
		return session;
	}

	private Session getSessionWithoutFilters(){
		Session session = sessionFactory.getCurrentSession();
		if(this.filterConfig.get() != null && this.filterConfig.get().size()>0){
			for(String filterName:this.filterConfig.get()){
				session.disableFilter(filterName);
			}
			//TODO Do we need to set NULL in thread local context ???
			this.filterConfig.set(null);
		}
		return session;
	}
	
	/**
	 * @return the sessionFactory
	 */
	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return the daoFilterMappings
	 */
	@Deprecated
	public Map<String, List<String>> getDaoFilterMappings() {
		return daoFilterMappings;
	}

	/**
	 * @param daoFilterMappings the daoFilterMappings to set
	 */
	@Deprecated
	public void setDaoFilterMappings(Map<String, List<String>> daoFilterMappings) {
		this.daoFilterMappings = daoFilterMappings;
	}
}