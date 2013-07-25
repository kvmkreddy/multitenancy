package org.os.javaee.orm.multitenancy.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.mapping.PersistentClass;
import org.os.javaee.orm.multitenancy.hibernate.mapper.IDynamicFilterConfigMapper;
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

	private static final Logger log = Logger.getLogger(MultiTenancyLocalSessionFactoryBean.class);
	private static final String bannerStart = "\n****************** FILTER INFO FOR MULTITENANACY START *********************\n";
	private static final String bannerEnd = "\n****************** FILTER INFO FOR MULTITENANACY COMPLETED *********************\n";
	
	private List<FilterInfo> filterList = new ArrayList<FilterInfo>();
	private Map<String,List<String>> filterMappings = new HashMap<String,List<String>>();
	private Map<String,List<FilterInfo>> extendedFilterMappings = new HashMap<String,List<FilterInfo>>();

	private IDynamicFilterConfigMapper filterConfigMapper = null;
	
	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate4.LocalSessionFactoryBean#buildSessionFactory(org.springframework.orm.hibernate4.LocalSessionFactoryBuilder)
	 */
	@Override
	protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sfb) {
		filterConfigMapper.addDynamicFilters(super.getConfiguration(), new ArrayList<String>());
		return super.buildSessionFactory(sfb);
	}
	
/*	 (non-Javadoc)
	 * @see org.springframework.orm.hibernate4.LocalSessionFactoryBean#buildSessionFactory(org.springframework.orm.hibernate4.LocalSessionFactoryBuilder)
	 
	@Override
	protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sfb) {
		Map<String,FilterInfo> filterDefinitionMap = new HashMap<String,FilterInfo>();
		if(this.getFilterList() != null && this.getFilterList().size()>0){
			for(FilterInfo filterInfo:this.getFilterList()){
				filterDefinitionMap.put(filterInfo.getName(), filterInfo);
				super.getConfiguration().addFilterDefinition(new FilterDefinition(filterInfo.getName(), filterInfo.getDefaultCondition(), filterInfo.getParametersType()));
			}
		}
		
		super.getConfiguration().buildMappings();
		
		for(Iterator<? extends PersistentClass> iter = this.getConfiguration().getClassMappings();iter.hasNext();){
			PersistentClass pClass = iter.next();
			if(getFilterMappings().containsKey(pClass.getEntityName())){
				for(String filterName:getFilterMappings().get(pClass.getEntityName())){
					FilterInfo filterInfo = filterDefinitionMap.get(filterName); 
					pClass.addFilter(filterInfo.getName(), (filterInfo.getCondition() != null ? filterInfo.getCondition():filterInfo.getDefaultCondition()), false, null, null);
					
					log.info(bannerStart+"Enabling filter(with default condition) -->:"+(filterInfo.getName())+"\n for Entity Name -->:"+(pClass.getEntityName())+"\n And Total Filters -->:"+(pClass.getFilters().size())+bannerEnd);
				}
			}
			
			if(getExtendedFilterMappings().containsKey(pClass.getEntityName())){
				for(FilterInfo filterInfo:getExtendedFilterMappings().get(pClass.getEntityName())){
					if(! getFilterMappings().get(pClass.getEntityName()).contains(filterInfo.getName())){
						pClass.addFilter(filterInfo.getName(), (filterInfo.getCondition() != null ? filterInfo.getCondition():filterDefinitionMap.get(filterInfo).getDefaultCondition()), false, null, null);
						log.info(bannerStart+"Enabling filter(with condition)-->:"+(filterInfo.getName())+"\n for Entity Name -->:"+(pClass.getEntityName())+"\n And Total Filters -->:"+(pClass.getFilters().size())+bannerEnd);
					}else{
						log.info(bannerStart+"Filter(with name) -->:"+(filterInfo.getName())+"\n is already enabled for Entity Name -->:"+(pClass.getEntityName())+"\n And Total Filters -->:"+(pClass.getFilters().size())+bannerEnd);
					}
				}
			}			
		}
		return super.buildSessionFactory(sfb);

		*//**
		 * TODO Check the How we can implement dynamic filter creation (in case of lazy entity scan/parse) at runtime.
		 *//*
	}
*/
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
	/**
	 * @return the filterMappings
	 */
	public Map<String, List<String>> getFilterMappings() {
		return filterMappings;
	}
	/**
	 * @param filterMappings the filterMappings to set
	 */
	public void setFilterMappings(Map<String, List<String>> filterMappings) {
		this.filterMappings = filterMappings;
	}
	/**
	 * @return the extendedFilterMappings
	 */
	public Map<String, List<FilterInfo>> getExtendedFilterMappings() {
		return extendedFilterMappings;
	}
	/**
	 * @param extendedFilterMappings the extendedFilterMappings to set
	 */
	public void setExtendedFilterMappings(
			Map<String, List<FilterInfo>> extendedFilterMappings) {
		this.extendedFilterMappings = extendedFilterMappings;
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