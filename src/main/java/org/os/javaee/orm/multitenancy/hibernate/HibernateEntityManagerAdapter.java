package org.os.javaee.orm.multitenancy.hibernate;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.os.javaee.orm.multitenancy.annotations.EnableFilter;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;
import org.os.javaee.orm.multitenancy.entity.IEntityManagerAdapter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Title: HibernateEntityManager</p>
 * <p><b>Description:</b> HibernateEntityManager </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@ReferenceImplementation(description="This is a reference implementation of Hibernate Entity Manager which is enaling/disabling hibernate filters through <code>org.os.javaee.orm.multitenancy.hibernate.SessionFactory</code>")
@MultiTenancy(strategy=Strategy.DISCRIMINATOR)
@EnableFilter(filterNames={"tenantFilter"})
@Transactional
@Repository
public class HibernateEntityManagerAdapter implements IEntityManagerAdapter{

	private SessionFactory sessionFactory = null;
	private static final Logger log = Logger.getLogger(HibernateEntityManagerAdapter.class);
	
	@Transactional(rollbackFor = Exception.class)
    public void create(final Object newEntity){
		persist(newEntity);
	}
	
    @Transactional(readOnly = true)
    public Object read(final Class<?> entityClass, final int id){
		return this.get(entityClass, id);
	}
	
    @Transactional(readOnly = true)
    public Object findById(final Class<?> entityClass, final int id) {
        return getCurrentSession().get(entityClass, id);
    }
    
    @Transactional(readOnly = true)
    public Object get(final Class<?> entityClass, final int id) {
        return getDefaultSession().get(entityClass, id);
    }
	
    @Transactional(readOnly = true)
    public List<?> findAll(final Class<?> entityClass) {
        return getCurrentSession().createQuery(
                "from " + entityClass.getName()).list();
    }

	@Transactional(rollbackFor = Exception.class)
    public void persist(final Object newEntity) {
        Session session = getCurrentSession();
        log.debug("Entity Info-->:"+(newEntity));
        session.persist(newEntity);
    }

	@Transactional(rollbackFor = Exception.class)
    public Serializable save(final Object newEntity) {
        Session session = getCurrentSession();
        log.debug("Entity Info is:-->:"+(newEntity));
        return session.save(newEntity);
    }
    
	@Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(final Object newEntity) {
        Session session = getDefaultSession();
        log.debug("Entity Info-->:"+(newEntity));
        session.saveOrUpdate(newEntity);
    }
	
	@Transactional(rollbackFor = Exception.class)
    public Object update(final Object peristentEntity) {
        Session session = getDefaultSession();
        return session.merge(peristentEntity);
    }
    
	@Transactional(rollbackFor = Exception.class)
    public void delete(final Object peristentEntity) {
        Session session = getCurrentSession();
        session.delete(peristentEntity);
    }

	public Object getDelegate(){
		return getDefaultSession();
	}
	
	public SessionFactory getSessionFactory() { return sessionFactory; }
	public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
	
	protected Session getCurrentSession(){
		return getSessionFactory().getCurrentSession(this.getClass());
	}
	
	protected Session getDefaultSession(){
		return getSessionFactory().getCurrentSession();
	}
	
}