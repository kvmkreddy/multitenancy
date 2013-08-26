package org.os.javaee.orm.multitenancy.hibernate;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.os.javaee.orm.multitenancy.hibernate.mapper.filterConfig.IDynamicFilterConfigMapper;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

/**
 * <p>Title: MultiTenancyLocalSessionFactoryBean</p>
 * <p><b>Description:</b> MultiTenancyLocalSessionFactoryBean</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class MultiTenancyLocalSessionFactoryBean extends LocalSessionFactoryBean {

	private IDynamicFilterConfigMapper filterConfigMapper = null;
	
	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate4.LocalSessionFactoryBean#buildSessionFactory(org.springframework.orm.hibernate4.LocalSessionFactoryBuilder)
	 */
	@Override
	protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sfb) {
		filterConfigMapper.addDynamicFilters(super.getConfiguration(), new ArrayList<String>());
		return super.buildSessionFactory(sfb);
	}
	
	/**
	 * @return the filterConfigMapper
	 */
	public IDynamicFilterConfigMapper getFilterConfigMapper() {
		return filterConfigMapper;
	}

	/**
	 * @param filterConfigMapper the filterConfigMapper to set
	 */
	public void setFilterConfigMapper(IDynamicFilterConfigMapper filterConfigMapper) {
		this.filterConfigMapper = filterConfigMapper;
	}
}