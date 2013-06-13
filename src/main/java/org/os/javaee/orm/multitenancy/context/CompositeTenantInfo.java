package org.os.javaee.orm.multitenancy.context;

import java.io.Serializable;

import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;

/**
 * <p>Title: CompositeTenantInfo</p>
 * <p><b>Description:</b> CompositeTenantInfo</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@ReferenceImplementation(description="This is a reference implementation of composite tenant info.")
public class CompositeTenantInfo implements Serializable {
	private static final long serialVersionUID = 2381212043314252041L;
	
	private int tenantIdOne;
	private int tenantIdTwo;
	private int tenantIdThree;
	
	public CompositeTenantInfo() {	}

	public CompositeTenantInfo(int tenantIdOne, int tenantIdTwo, int tenantIdThree) {
		this.tenantIdOne = tenantIdOne;
		this.tenantIdTwo = tenantIdTwo;
		this.tenantIdThree = tenantIdThree;
	}

	/**
	 * @return the tenantIdOne
	 */
	public int getTenantIdOne() {
		return tenantIdOne;
	}

	/**
	 * @param tenantIdOne the tenantIdOne to set
	 */
	public void setTenantIdOne(int tenantIdOne) {
		this.tenantIdOne = tenantIdOne;
	}

	/**
	 * @return the tenantIdTwo
	 */
	public int getTenantIdTwo() {
		return tenantIdTwo;
	}

	/**
	 * @param tenantIdTwo the tenantIdTwo to set
	 */
	public void setTenantIdTwo(int tenantIdTwo) {
		this.tenantIdTwo = tenantIdTwo;
	}

	/**
	 * @return the tenantIdThree
	 */
	public int getTenantIdThree() {
		return tenantIdThree;
	}

	/**
	 * @param tenantIdThree the tenantIdThree to set
	 */
	public void setTenantIdThree(int tenantIdThree) {
		this.tenantIdThree = tenantIdThree;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompositeTenantInfo [tenantIdOne=" + tenantIdOne
				+ ", tenantIdTwo=" + tenantIdTwo + ", tenantIdThree="
				+ tenantIdThree + "]";
	}

}
