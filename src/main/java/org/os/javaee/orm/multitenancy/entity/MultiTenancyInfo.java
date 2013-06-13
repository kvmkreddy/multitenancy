/**
 * <p>Title: MultiTenancyInfo</p>
 * <p><b>Description:</b> MultiTenancyInfo</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
package org.os.javaee.orm.multitenancy.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;

/**
 * <p>Title: MultiTenancyInfo</p>
 * <p><b>Description:</b> MultiTenancyInfo</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@Embeddable
@MultiTenancy(strategy=Strategy.DISCRIMINATOR)
public class MultiTenancyInfo implements Serializable{

	
	private static final long serialVersionUID = 8092350254739056045L;

	private int tenantId;
	
	public MultiTenancyInfo() {	}

	public MultiTenancyInfo(int tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the tenantId
	 */
	public int getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
}
