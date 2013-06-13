package org.os.javaee.orm.multitenancy.context;

import java.io.Serializable;

/**
 * <p>Title: ITenantContext</p>
 * <p><b>Description:</b> ITenantContext</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public interface ITenantContext<T> extends Serializable{

	public T getTenantInfo();

	public void setTenantInfo(T tenantInfo);
}