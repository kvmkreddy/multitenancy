package org.os.javaee.orm.multitenancy.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.os.javaee.orm.multitenancy.annotations.EnableFilter;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;
import org.os.javaee.orm.multitenancy.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Title: MultiTenancyEnabledDAO</p>
 * <p><b>Description:</b> MultiTenancyEnabledDAO</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@ReferenceImplementation(description="This is a reference implementation of DAO which is enaling/disabling hibernate filters through <code>org.os.javaee.orm.multitenancy.hibernate.SessionFactory</code>")
@MultiTenancy(strategy=Strategy.DISCRIMINATOR)
@EnableFilter(filterNames={"tenantFilter"})
@Transactional
@Repository
@Deprecated
public class MultiTenancyEnabledDAO<T extends Serializable> {

	private SessionFactory sessionFactory = null;
	private static final Logger log = Logger.getLogger(MultiTenancyEnabledDAO.class);
	
	@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public T findById(final Class<T> entityClass, final int id) {
        return (T) getCurrentSession().get(entityClass, id);
    }
    
	@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public T get(final Class<T> entityClass, final int id) {
        return (T) getDefaultSession().get(entityClass, id);
    }
	
	@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<T> findAll(final Class<T> entityClass) {
        return getCurrentSession().createQuery(
                "from " + entityClass.getName()).list();
    }

	@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<T> getListByNamedQuery(String queryName) {
        return getCurrentSession().getNamedQuery(queryName).list();
    }

	@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<T> getListByNamedNativeQuery(String queryString) {
        return getCurrentSession().createSQLQuery(queryString).list();
    }
	
	@Transactional(rollbackFor = Exception.class)
    public void persist(final T newEntity) {
        Session session = getCurrentSession();
        log.debug("Entity Info-->:"+(newEntity));
        session.persist(newEntity);
    }

	@Transactional(rollbackFor = Exception.class)
    public Serializable save(final T newEntity) {
        Session session = getCurrentSession();
        log.debug("Entity Info is:-->:"+(newEntity));
        return session.save(newEntity);
    }
    
	@Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(final T newEntity) {
        Session session = getDefaultSession();
        log.debug("Entity Info-->:"+(newEntity));
        session.saveOrUpdate(newEntity);
    }
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
    public T update(final T peristentEntity) {
        Session session = getDefaultSession();
        return (T)session.merge(peristentEntity);
    }
    
	@Transactional(rollbackFor = Exception.class)
    public void delete(final T peristentEntity) {
        Session session = getCurrentSession();
        session.delete(peristentEntity);
    }

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getCurrentSession(){
		return getSessionFactory().getCurrentSession(this.getClass());
	}
	
	protected Session getDefaultSession(){
		return getSessionFactory().getCurrentSession();
	}
}