package org.os.javaee.orm.multitenancy.hibernate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.FilterDefinition;
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

	private List<FilterInfo> filterList = new ArrayList<FilterInfo>();
	
	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate4.LocalSessionFactoryBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws IOException {
		super.afterPropertiesSet();
	}

	
	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate4.LocalSessionFactoryBean#buildSessionFactory(org.springframework.orm.hibernate4.LocalSessionFactoryBuilder)
	 */
	@Override
	protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sfb) {
		if(this.getFilterList() != null && this.getFilterList().size()>0){
			for(FilterInfo filterInfo:this.getFilterList()){
				super.getConfiguration().addFilterDefinition(new FilterDefinition(filterInfo.getName(), filterInfo.getDefaultCondition(), filterInfo.getParametersType()));
			}
		}
		return super.buildSessionFactory(sfb);
	}


	/**
	 * @return the filterList
	 */
	public List<FilterInfo> getFilterList() {
		return filterList;
	}

	/**
	 * @param filterList the filterList to set
	 */
	public void setFilterList(List<FilterInfo> filterList) {
		this.filterList = filterList;
	}
}