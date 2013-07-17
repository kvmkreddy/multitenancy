package org.os.javaee.orm.multitenancy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.os.javaee.orm.multitenancy.annotations.InjectTenantInfo;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;
/**
 * <p>Title: MultiTenancyEnabledEntity</p>
 * <p><b>Description:</b> MultiTenancyEnabledEntity</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@Entity
@Table(name="CLIENT")
@MultiTenancy(strategy=Strategy.DISCRIMINATOR)
@ReferenceImplementation(description="This is a reference implementation of Enity which will be injected with tenant info at runtime.")
//@Filter(name = "tenantFilter", condition = ":tenantParam=TENANT_ID")
//@Filter(name = "tenantFilter")
public class MultiTenancyEnabledEntity implements Serializable{

	private static final long serialVersionUID = -8320043017606570740L;

	private int id;
	private int tenantId;
	private String name;
	
	/**
	 * @return the tenantId
	 */
	@Column(name = "TENANT_ID")
	public int getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	@InjectTenantInfo()
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MultiTenancyEnabledEntity [id=" + id + ", tenantId=" + tenantId
				+ ", name=" + name + "]";
	}
}