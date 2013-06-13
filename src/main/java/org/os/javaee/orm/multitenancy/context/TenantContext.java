package org.os.javaee.orm.multitenancy.context;

import java.io.Serializable;

/**
 * <p>Title: TenantContext</p>
 * <p><b>Description:</b> TenantContext</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class TenantContext implements Serializable{

	
	private static final long serialVersionUID = -4907209540217220934L;
	
	private String tenantId = null;
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "TenantContext [tenantId=" + tenantId + "]";
	}
}