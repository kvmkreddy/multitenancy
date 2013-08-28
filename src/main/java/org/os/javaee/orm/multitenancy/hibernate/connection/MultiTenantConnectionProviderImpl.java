package org.os.javaee.orm.multitenancy.hibernate.connection;

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
	
	private ConnectionProviderProducer connectionProviderProducer;
	
	@Override
	protected ConnectionProvider getAnyConnectionProvider() {
		return selectConnectionProvider(ConnectionProviderProducer.DEFAULT_CONNECTION_PROVIDER);
	}

	@Override
	protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
		return getConnectionProviderProducer().getConnectionProvider(tenantIdentifier);
	}

	/**
	 * @return the connectionProviderProducer
	 */
	public ConnectionProviderProducer getConnectionProviderProducer() {
		return connectionProviderProducer;
	}

	/**
	 * @param connectionProviderProducer the connectionProviderProducer to set
	 */
	public void setConnectionProviderProducer(ConnectionProviderProducer connectionProviderProducer) {
		this.connectionProviderProducer = connectionProviderProducer;
	}

	
}