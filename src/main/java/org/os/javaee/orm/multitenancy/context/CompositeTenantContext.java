package org.os.javaee.orm.multitenancy.context;

import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;

/**
 * <p>Title: CompositeTenantContext</p>
 * <p><b>Description:</b> CompositeTenantContext</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@ReferenceImplementation
public class CompositeTenantContext implements ITenantContext<CompositeTenantInfo>{

	private static final long serialVersionUID = -40522816930050102L;
	
	private CompositeTenantInfo tenantInfo = null;


	/* (non-Javadoc)
	 * @see org.os.javaee.orm.multitenancy.context.ITenantContext#setTenantInfo(java.lang.Object)
	 */
	@Override
	public void setTenantInfo(CompositeTenantInfo tenantInfo) {
		this.tenantInfo = tenantInfo;
	}

	/* (non-Javadoc)
	 * @see org.os.javaee.orm.multitenancy.context.ITenantContext#getTenantInfo()
	 */
	@Override
	public CompositeTenantInfo getTenantInfo() {
		return tenantInfo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompositeTenantContext [tenantInfo=" + tenantInfo + "]";
	}
}
