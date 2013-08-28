package org.os.javaee.orm.multitenancy.hibernate.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

/**
 * <p>Title: DataSourceConnectionProviderImpl</p>
 * <p><b>Description:</b> DataSourceConnectionProviderImpl</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class DataSourceConnectionProviderImpl implements ConnectionProvider{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5684795751264042021L;

	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeConnection(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

}
