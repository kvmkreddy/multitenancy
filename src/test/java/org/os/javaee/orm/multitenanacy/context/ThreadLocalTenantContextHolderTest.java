/**
 * <p>Title: ThreadLocalTenantContextHolderTest</p>
 * <p><b>Description:</b> ThreadLocalTenantContextHolderTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
package org.os.javaee.orm.multitenanacy.context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.os.javaee.orm.multitenancy.context.ITenantContextHolder;
import org.os.javaee.orm.multitenancy.context.TenantContext;
import org.os.javaee.orm.multitenancy.context.ThreadLocalTenantContextHolder;

/**
 * <p>Title: ThreadLocalTenantContextHolderTest</p>
 * <p><b>Description:</b> ThreadLocalTenantContextHolderTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class ThreadLocalTenantContextHolderTest {

	ITenantContextHolder contextHolder = null;  
			
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		contextHolder = new ThreadLocalTenantContextHolder();
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.os.javaee.orm.multitenancy.context.ThreadLocalTenantContextHolder#getTenantContext()}.
	 */
	@Test
	public final void testGetTenantContext() {
		populateContents();
		Assert.assertNotNull("Successfully set contents into ContextHolder", contextHolder.getTenantContext());
	}

	private void populateContents() {
		TenantContext context = new TenantContext();
		context.setTenantInfo("Some Tenant Info");
		contextHolder.setTenantContext(context);
	}

	/**
	 * Test method for {@link org.os.javaee.orm.multitenancy.context.ThreadLocalTenantContextHolder#setTenantContext(org.os.javaee.orm.multitenancy.context.TenantContext)}.
	 */
	@Test
	public final void testSetTenantContext() {
		populateContents();
		Assert.assertNotNull("Successfully set contents into ContextHolder", contextHolder.getTenantContext().getTenantInfo());
	}

}
