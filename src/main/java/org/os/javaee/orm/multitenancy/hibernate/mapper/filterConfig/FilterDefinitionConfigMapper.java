package org.os.javaee.orm.multitenancy.hibernate.mapper.filterConfig;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.FilterDefinition;
import org.os.javaee.orm.multitenancy.hibernate.FilterInfo;

/**
 * <p>Title: FilterDefinitionConfigMapper</p>
 * <p><b>Description:</b> FilterDefinitionConfigMapper</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class FilterDefinitionConfigMapper extends AbstractDynamicFilterConfigMapper{
	
	private List<FilterInfo> filterList = new ArrayList<FilterInfo>();
	
	/* (non-Javadoc)
	 * @see org.os.javaee.orm.multitenancy.hibernate.mapper.IDynamicFilterConfigMapper#addDynamicFilters(org.hibernate.cfg.Configuration, java.util.List)
	 */
	@Override
	public void addDynamicFilters(Configuration config, List<String> existingFilters) {
		if(this.getFilterList() != null && this.getFilterList().size()>0){
			for(FilterInfo filterInfo:this.getFilterList()){
				existingFilters.add(filterInfo.getName());
				config.addFilterDefinition(new FilterDefinition(filterInfo.getName(), filterInfo.getDefaultCondition(), filterInfo.getParametersType()));
			}
		}
		
		config.buildMappings();
		
		super.invokeNextDynamicFilterConfigMapper(config,existingFilters);
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