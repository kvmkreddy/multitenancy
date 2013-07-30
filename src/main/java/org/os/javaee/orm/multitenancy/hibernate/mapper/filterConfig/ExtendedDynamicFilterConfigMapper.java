package org.os.javaee.orm.multitenancy.hibernate.mapper.filterConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.os.javaee.orm.multitenancy.hibernate.FilterInfo;

/**
 * <p>Title: ExtendedDynamicFilterConfigMapper</p>
 * <p><b>Description:</b> ExtendedDynamicFilterConfigMapper</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class ExtendedDynamicFilterConfigMapper extends DefaultDynamicFilterConfigMapper {

	private static final Logger log = Logger.getLogger(ExtendedDynamicFilterConfigMapper.class);
	
	private Map<String,List<FilterInfo>> extendedFilterMappings = new HashMap<String,List<FilterInfo>>();
	
	/* (non-Javadoc)
	 * @see org.os.javaee.orm.multitenancy.hibernate.mapper.IDynamicFilterConfigMapper#addDynamicFilters(org.hibernate.cfg.Configuration, java.util.List)
	 */
	@Override
	public void addDynamicFilters(Configuration config, List<String> existingFilters) {
		for(Iterator<? extends PersistentClass> iter = config.getClassMappings();iter.hasNext();){
			PersistentClass pClass = iter.next();
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
		super.invokeNextDynamicFilterConfigMapper(config,existingFilters);
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
}