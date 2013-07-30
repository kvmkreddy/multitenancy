package org.os.javaee.orm.multitenancy.hibernate.mapper.filterConfig;

import java.util.List;

import org.hibernate.cfg.Configuration;


/**
 * <p>Title: IDynamicFilterConfigMapper</p>
 * <p><b>Description:</b> IDynamicFilterConfigMapper</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public interface IDynamicFilterConfigMapper {

	public String bannerStart = "\n****************** FILTER INFO FOR MULTITENANACY START *********************\n";
	public String bannerEnd = "\n****************** FILTER INFO FOR MULTITENANACY COMPLETED *********************\n";

	public void addDynamicFilters(Configuration config, List<String> existingFilters);
}
