package org.os.javaee.orm.multitenancy.eclipse.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.os.javaee.orm.multitenancy.annotations.InjectTenantInfo;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;

/**
 * <p>Title: EclipseMultiTenancyEnabledEntity</p>
 * <p><b>Description:</b> EclipseMultiTenancyEnabledEntity</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@Entity
@Table(name="CLIENT") 
@Multitenant
@TenantDiscriminatorColumn(name = "TENANT_ID", contextProperty = "employee-tenant.tenantId")
@ReferenceImplementation(description="This is a reference implementation of Enity which will be injected with tenant info at runtime by using ECLIPSE LINK ORM.")
@NamedQuery(name="findAllEntitiesQuery", query="SELECT e FROM EclipseMultiTenancyEnabledEntity e")
public class EclipseMultiTenancyEnabledEntity implements java.io.Serializable{

	private static final long serialVersionUID = 2964502034614223603L;

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
		return "EclipseMultiTenancyEnabledEntity [id=" + id + ", tenantId=" + tenantId
				+ ", name=" + name + "]";
	}
	
}
