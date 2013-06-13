package org.os.javaee.orm.multitenancy.context;

import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;

/**
 * <p>Title: TenantContext</p>
 * <p><b>Description:</b> TenantContext</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@ReferenceImplementation
public class TenantContext implements ITenantContext<String>{

	
	private static final long serialVersionUID = -4907209540217220934L;
	
	private String tenantId = null;
	
	/* (non-Javadoc)
	 * @see org.os.javaee.orm.multitenancy.context.ITenantContext#getTenantId()
	 */
	@Override
	public String getTenantInfo() {
		return tenantId;
	}

	/* (non-Javadoc)
	 * @see org.os.javaee.orm.multitenancy.context.ITenantContext#setTenantInfo(java.lang.Object)
	 */
	@Override
	public void setTenantInfo(String tenantInfo) {
			this.tenantId = (String)tenantInfo;
	}

	@Override
	public String toString() {
		return "TenantContext [tenantId=" + tenantId + "]";
	}
}