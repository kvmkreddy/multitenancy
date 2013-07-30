package org.os.javaee.orm.multitenancy.hibernate.mapper.filterConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.os.javaee.orm.multitenancy.hibernate.FilterInfo;

/**
 * <p>Title: DefaultDynamicFilterConfigMapper</p>
 * <p><b>Description:</b> DefaultDynamicFilterConfigMapper</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class DefaultDynamicFilterConfigMapper extends AbstractDynamicFilterConfigMapper{

	private static final Logger log = Logger.getLogger(DefaultDynamicFilterConfigMapper.class);
	
	protected Map<String,List<String>> filterMappings = new HashMap<String,List<String>>();
	protected Map<String,FilterInfo> filterDefinitionMap = new HashMap<String,FilterInfo>();
	protected List<FilterInfo> filterList = new ArrayList<FilterInfo>();
	
	@Override
	public void addDynamicFilters(Configuration config, List<String> existingFilters) {
		for(Iterator<? extends PersistentClass> iter = config.getClassMappings();iter.hasNext();){
			PersistentClass pClass = iter.next();
			if(getFilterMappings().containsKey(pClass.getEntityName())){
				for(String filterName:getFilterMappings().get(pClass.getEntityName())){
					FilterInfo filterInfo = filterDefinitionMap.get(filterName); 
					pClass.addFilter(filterInfo.getName(), (filterInfo.getCondition() != null ? filterInfo.getCondition():filterInfo.getDefaultCondition()), false, null, null);
					existingFilters.add(filterInfo.getName());
					existingFilters.get(1);
					log.info(bannerStart+"Enabling filter(with default condition) -->:"+(filterInfo.getName())+"\n for Entity Name -->:"+(pClass.getEntityName())+"\n And Total Filters -->:"+(pClass.getFilters().size())+bannerEnd);
				}
			}
		}
		super.invokeNextDynamicFilterConfigMapper(config,existingFilters);
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
		if(this.getFilterList() != null && this.getFilterList().size()>0){
			for(FilterInfo filterInfo:this.getFilterList()){
				filterDefinitionMap.put(filterInfo.getName(), filterInfo);
			}
		}	
	}

	/**
	 * @return the filterDefinitionMap
	 */
	public Map<String, FilterInfo> getFilterDefinitionMap() {
		return filterDefinitionMap;
	}

	/**
	 * @param filterDefinitionMap the filterDefinitionMap to set
	 */
	public void setFilterDefinitionMap(Map<String, FilterInfo> filterDefinitionMap) {
		this.filterDefinitionMap = filterDefinitionMap;
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