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

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;

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
@ReferenceImplementation(description="This is a reference implementation of composite tenant info.")
public class MultiTenancyInfo implements Serializable{
	
	private static final long serialVersionUID = 8092350254739056045L;

	private int tenantIdOne=0;
	private int tenantIdTwo=0;
	private int tenantIdThree=0;
	
	public MultiTenancyInfo() {	}

	public MultiTenancyInfo(int tenantIdOne, int tenantIdTwo, int tenantIdThree) {
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
		return "MultiTenancyInfo [tenantIdOne=" + tenantIdOne
				+ ", tenantIdTwo=" + tenantIdTwo + ", tenantIdThree="
				+ tenantIdThree + "]";
	}
}