package org.os.javaee.orm.multitenancy.entity;

import java.io.Serializable;

import org.os.javaee.orm.multitenancy.annotations.EnableFilter;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;

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
public class MultiTenancyEnabledDAO<T extends Serializable> {

	private IEntityManagerAdapter entityManagerAdapter = null;
	
	/**
	 * @param newEntity
	 * @see org.os.javaee.orm.multitenancy.entity.IEntityManagerAdapter#create(java.lang.Object)
	 */
	public void create(Object newEntity) {
		entityManagerAdapter.create(newEntity);
	}
	
	/**
	 * @param newEntity
	 * @return
	 * @see org.os.javaee.orm.multitenancy.entity.IEntityManagerAdapter#save(java.lang.Object)
	 */
	public Serializable save(T newEntity) {
		return entityManagerAdapter.save(newEntity);
	}

	/**
	 * @param entityClass
	 * @param id
	 * @return
	 * @see org.os.javaee.orm.multitenancy.entity.IEntityManagerAdapter#read(java.lang.Class, int)
	 */
	@SuppressWarnings("unchecked")
	public T read(Class<T> entityClass, int id) {
		return (T)entityManagerAdapter.read(entityClass, id);
	}
	
	/**
	 * @param peristentEntity
	 * @return
	 * @see org.os.javaee.orm.multitenancy.entity.IEntityManagerAdapter#update(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public T update(T peristentEntity) {
		return (T)entityManagerAdapter.update(peristentEntity);
	}
	
	/**
	 * @param peristentEntity
	 * @see org.os.javaee.orm.multitenancy.entity.IEntityManagerAdapter#delete(java.lang.Object)
	 */
	public void delete(T peristentEntity) {
		entityManagerAdapter.delete(peristentEntity);
	}
	
	/**
	 * @return
	 * @see org.os.javaee.orm.multitenancy.entity.IEntityManagerAdapter#getDelegate()
	 */
	public Object getDelegate() {
		return entityManagerAdapter.getDelegate();
	}

	/**
	 * @return the entityManagerAdapter
	 */
	public IEntityManagerAdapter getEntityManagerAdapter() {
		return entityManagerAdapter;
	}

	/**
	 * @param entityManagerAdapter the entityManagerAdapter to set
	 */
	public void setEntityManagerAdapter(IEntityManagerAdapter entityManagerAdapter) {
		this.entityManagerAdapter = entityManagerAdapter;
	}
}