package org.os.javaee.orm.multitenancy.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import org.os.javaee.orm.multitenancy.annotations.InjectTenantInfo;

/**
 * <p>Title: CompositeMultiTenancyEnabledEntity</p>
 * <p><b>Description:</b> CompositeMultiTenancyEnabledEntity</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@MappedSuperclass
public class CompositeMultiTenancyEnabledEntity implements Serializable{

	private static final long serialVersionUID = -6877598014512888223L;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(column=@Column(name="TENANT_ID"),name="tenantId")
	})
	private MultiTenancyInfo mtInfo;

	public MultiTenancyInfo getMtInfo() {
		return mtInfo;
	}

	@InjectTenantInfo
	public void setMtInfo(MultiTenancyInfo mtInfo) {
		this.mtInfo = mtInfo;
	}
}