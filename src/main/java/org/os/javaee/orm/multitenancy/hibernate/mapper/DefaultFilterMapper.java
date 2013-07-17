package org.os.javaee.orm.multitenancy.hibernate.mapper;

import org.hibernate.Filter;
import org.os.javaee.orm.multitenancy.context.ITenantContext;
import org.os.javaee.orm.multitenancy.context.ITenantContextHolder;

/**
 * <p>Title: DefaultFilterMapper</p>
 * <p><b>Description:</b> DefaultFilterMapper</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class DefaultFilterMapper implements IFilterMapper {

	private static final String DEFAULT_FILTER_PARAM_NAME="tenantParam";
	
	private ITenantContextHolder contextHolder;
	private String filterParamName = DEFAULT_FILTER_PARAM_NAME;
	
	@SuppressWarnings("unchecked")
	@Override
	public void map(Filter filter) {
		ITenantContext<String> tenantContext = (ITenantContext<String>)contextHolder.getTenantContext();
		filter.setParameter(this.getFilterParamName(),Integer.valueOf(tenantContext.getTenantInfo()));
	}

	/**
	 * @return the contextHolder
	 */
	public ITenantContextHolder getContextHolder() {
		return contextHolder;
	}

	/**
	 * @param contextHolder the contextHolder to set
	 */
	public void setContextHolder(ITenantContextHolder contextHolder) {
		this.contextHolder = contextHolder;
	}

	/**
	 * @return the filterParamName
	 */
	public String getFilterParamName() {
		return filterParamName;
	}

	/**
	 * @param filterParamName the filterParamName to set
	 */
	public void setFilterParamName(String filterParamName) {
		this.filterParamName = filterParamName;
	}
}
