package org.os.javaee.orm.multitenancy.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.service.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

/**
 * <p>Title: MultiTenantConnectionProviderImpl</p>
 * <p><b>Description:</b> MultiTenantConnectionProviderImpl</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class MultiTenantConnectionProviderImpl extends AbstractMultiTenantConnectionProvider {

	private static final long serialVersionUID = -3694115158454455013L;
	
	private ConnectionProvider defaultConnectionProvider;
	private Map<String,ConnectionProvider> connectionProviderMap = new HashMap<String, ConnectionProvider>();

	@Override
	protected ConnectionProvider getAnyConnectionProvider() {
		return getDefaultConnectionProvider();
	}

	@Override
	protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
		return (connectionProviderMap.containsKey(tenantIdentifier))?connectionProviderMap.get(tenantIdentifier):getDefaultConnectionProvider();
	}

	/**
	 * @return the defaultConnectionProvider
	 */
	public ConnectionProvider getDefaultConnectionProvider() {
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
	public void setConnectionProviderMap(
			Map<String, ConnectionProvider> connectionProviderMap) {
		this.connectionProviderMap = connectionProviderMap;
	}
}