package org.os.javaee.orm.multitenancy.context;

/**
 * <p>Title: ITenantContextHolder</p>
 * <p><b>Description:</b> ITenantContextHolder</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public interface ITenantContextHolder {
	
	TenantContext getTenantContext();
	
	void setTenantContext(TenantContext context);
}