package org.os.javaee.orm.multitenancy.context;

/**
 * <p>Title: TenantContextHolderFactory</p>
 * <p><b>Description:</b> TenantContextHolderFactory</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class TenantContextHolderFactory {

	public static ITenantContextHolder getTenantContextHolder(){
		return new ThreadLocalTenantContextHolder();
	}
}
