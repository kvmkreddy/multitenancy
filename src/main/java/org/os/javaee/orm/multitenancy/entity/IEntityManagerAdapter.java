package org.os.javaee.orm.multitenancy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: IEntityManagerAdapter</p>
 * <p><b>Description:</b> IEntityManagerAdapter</p>
 * <p><b><ul><i>NOTE: THIS ADAPTER INTERFACE IS WIP. Will be modified in future. The implementations should be ready to accommodate the changes.</i></ul></b></p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public interface IEntityManagerAdapter{

	public Object findById(final Class<?> entityClass, final int id);
	
	public List<?> findAll(final Class<?> entityClass);
	
	public void create(final Object newEntity);
	
	public Serializable save(final Object newEntity);
	
	public Object read(final Class<?> entityClass, final int id);
	
	public Object update(final Object peristentEntity);
	
	public void delete(final Object peristentEntity);

	public Object getDelegate();

}
