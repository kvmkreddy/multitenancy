package org.os.javaee.orm.multitenancy.hibernate.mapper;

import org.hibernate.Filter;
import org.os.javaee.orm.multitenancy.annotations.ReferenceImplementation;
import org.os.javaee.orm.multitenancy.context.CompositeTenantInfo;
import org.os.javaee.orm.multitenancy.context.ITenantContext;
import org.os.javaee.orm.multitenancy.context.ITenantContextHolder;

/**
 * <p>Title: CompositeFilterMapper</p>
 * <p><b>Description:</b> CompositeFilterMapper</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@ReferenceImplementation(description="This is a reference implementation of composite tenant filter mapper.")
public class CompositeFilterMapper implements IFilterMapper {

	private static final String DEFAULT_FILTER_PARAM_NAME_ONE="tenantParamOne";
	private static final String DEFAULT_FILTER_PARAM_NAME_TWO="tenantParamTwo";
	private static final String DEFAULT_FILTER_PARAM_NAME_THREE="tenantParamThree";
	
	private ITenantContextHolder contextHolder;
	private String filterParamNameOne = DEFAULT_FILTER_PARAM_NAME_ONE;
	private String filterParamNameTwo = DEFAULT_FILTER_PARAM_NAME_TWO;
	private String filterParamNameThree = DEFAULT_FILTER_PARAM_NAME_THREE;
	

	/* (non-Javadoc)
	 * @see org.os.javaee.orm.multitenancy.hibernate.mapper.IFilterMapper#map(org.hibernate.Filter)
	 */
	@Override
	public void map(Filter filter) {
		@SuppressWarnings("unchecked")
		ITenantContext<CompositeTenantInfo> tenantContext = (ITenantContext<CompositeTenantInfo>)contextHolder.getTenantContext();
		filter.setParameter(this.getFilterParamNameOne(),Integer.valueOf(tenantContext.getTenantInfo().getTenantIdOne()));
		filter.setParameter(this.getFilterParamNameTwo(),Integer.valueOf(tenantContext.getTenantInfo().getTenantIdTwo()));
		filter.setParameter(this.getFilterParamNameThree(),Integer.valueOf(tenantContext.getTenantInfo().getTenantIdThree()));
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
	 * @return the filterParamNameOne
	 */
	public String getFilterParamNameOne() {
		return filterParamNameOne;
	}


	/**
	 * @param filterParamNameOne the filterParamNameOne to set
	 */
	public void setFilterParamNameOne(String filterParamNameOne) {
		this.filterParamNameOne = filterParamNameOne;
	}


	/**
	 * @return the filterParamNameTwo
	 */
	public String getFilterParamNameTwo() {
		return filterParamNameTwo;
	}


	/**
	 * @param filterParamNameTwo the filterParamNameTwo to set
	 */
	public void setFilterParamNameTwo(String filterParamNameTwo) {
		this.filterParamNameTwo = filterParamNameTwo;
	}


	/**
	 * @return the filterParamNameThree
	 */
	public String getFilterParamNameThree() {
		return filterParamNameThree;
	}


	/**
	 * @param filterParamNameThree the filterParamNameThree to set
	 */
	public void setFilterParamNameThree(String filterParamNameThree) {
		this.filterParamNameThree = filterParamNameThree;
	}
}