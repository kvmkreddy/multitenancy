package org.os.javaee.orm.multitenancy.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.os.javaee.orm.multitenancy.annotations.InjectTenantInfo;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy;
import org.os.javaee.orm.multitenancy.annotations.MultiTenancy.Strategy;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;

/**
 * <p>Title: CompositeMultiTenancyEnabledEntity</p>
 * <p><b>Description:</b> CompositeMultiTenancyEnabledEntity</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@Entity
@Table(name="COMP_CLIENT")
@MultiTenancy(strategy=Strategy.DISCRIMINATOR)
@ReferenceImplementation(description="This is a reference implementation of Enity which will be injected with composite tenant info at runtime.")
@NamedQuery(name="findAllCompositeMTEntitiesQuery", query="SELECT e FROM CompositeMultiTenancyEnabledEntity e")
public class CompositeMultiTenancyEnabledEntity implements Serializable{

	private static final long serialVersionUID = -6877598014512888223L;

	private MultiTenancyInfo mtInfo = new MultiTenancyInfo();
	private int id;
	private String name;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(column=@Column(name="CMP_TENANT_ID1"),name="tenantIdOne"),
		@AttributeOverride(column=@Column(name="CMP_TENANT_ID2"),name="tenantIdTwo"),
		@AttributeOverride(column=@Column(name="CMP_TENANT_ID3"),name="tenantIdThree")
	})
	public MultiTenancyInfo getMtInfo() {
		return mtInfo;
	}

	@InjectTenantInfo

	public void setMtInfo(MultiTenancyInfo mtInfo) {
		this.mtInfo=mtInfo;
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
}