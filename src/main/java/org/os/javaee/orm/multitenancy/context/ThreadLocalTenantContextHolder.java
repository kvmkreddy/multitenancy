package org.os.javaee.orm.multitenancy.context;


/**
 * <p>Title: ThreadLocalTenantContextHolder</p>
 * <p><b>Description:</b> ThreadLocalTenantContextHolder</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class ThreadLocalTenantContextHolder implements ITenantContextHolder {

	private ThreadLocal<ITenantContext<?>> holder = new ThreadLocal<ITenantContext<?>>();
	
	public ITenantContext<?> getTenantContext(){
		return holder.get();
	}
	
	@Override
	public void setTenantContext(ITenantContext<?> context) {
		holder.set(context);
		
	}
}
