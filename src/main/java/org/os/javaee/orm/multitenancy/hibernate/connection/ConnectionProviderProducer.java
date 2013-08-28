package org.os.javaee.orm.multitenancy.hibernate.connection;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

/**
 * <p>Title: ConnectionProviderProducer</p>
 * <p><b>Description:</b> ConnectionProviderProducer</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class ConnectionProviderProducer {

	public static final String DEFAULT_CONNECTION_PROVIDER = "DEFAULT_CONNECTION_PROVIDER";
	public static final String BLANK_STRING= "";


	private ConnectionProvider defaultConnectionProvider;
	private Map<String,ConnectionProvider> connectionProviderMap = new HashMap<String, ConnectionProvider>();

	private DataSource datasourceForAny;
	private Map<String, ? extends DataSource> tenanatSpecificDatasource = new HashMap<String, DataSource>();
	
	public ConnectionProvider getConnectionProvider(String tenantIdentifier){
		if(tenantIdentifier!= null && ! tenantIdentifier.trim().equals(BLANK_STRING)){
			return (tenantIdentifier.equalsIgnoreCase(DEFAULT_CONNECTION_PROVIDER)) ? 
						getDefaultConnectionProvider():
						(getConnectionProviderMap().containsKey(tenantIdentifier)? 
									getConnectionProviderMap().get(tenantIdentifier):
									getDefaultConnectionProvider());
		}else{
			return getDefaultConnectionProvider();	
		}
	}
	
	/**
	 * @return the defaultConnectionProvider
	 */
	public ConnectionProvider getDefaultConnectionProvider() {
		//return getConnectionProviderMap().get(DEFAULT_CONNECTION_PROVIDER);
		return defaultConnectionProvider;
	}

	/**
	 * @param defaultConnectionProvider the defaultConnectionProvider to set
	 */
	public void setDefaultConnectionProvider(ConnectionProvider defaultConnectionProvider) {
		this.defaultConnectionProvider = defaultConnectionProvider;
	}

	/**
	 * @return the connectionProviderMap
	 */
	public Map<String, ConnectionProvider> getConnectionProviderMap() {
		return connectionProviderMap;
	}

	/**
	 * @param connectionProviderMap the connectionProviderMap to set
	 */
	public void setConnectionProviderMap(Map<String, ConnectionProvider> connectionProviderMap) {
		this.connectionProviderMap = connectionProviderMap;
	}
}