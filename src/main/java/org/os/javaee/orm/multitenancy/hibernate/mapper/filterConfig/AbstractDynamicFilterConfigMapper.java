package org.os.javaee.orm.multitenancy.hibernate.mapper.filterConfig;

import java.util.List;

import org.hibernate.cfg.Configuration;

/**
 * <p>Title: AbstractDynamicFilterConfigMapper</p>
 * <p><b>Description:</b> AbstractDynamicFilterConfigMapper</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public abstract class AbstractDynamicFilterConfigMapper implements IDynamicFilterConfigMapper {

	private IDynamicFilterConfigMapper nextMapper;
	
	public void invokeNextDynamicFilterConfigMapper(Configuration config, List<String> existingFilters) {
		if(this.getNextMapper() != null){
			this.getNextMapper().addDynamicFilters(config, existingFilters);
		}
	}

	/**
	 * @return the nextFilter
	 */
	public IDynamicFilterConfigMapper getNextMapper() {
		return nextMapper;
	}

	/**
	 * @param nextFilter the nextFilter to set
	 */
	public void setNextMapper(IDynamicFilterConfigMapper nextMapper) {
		this.nextMapper = nextMapper;
	}
}
