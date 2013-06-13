package org.os.javaee.orm.multitenanacy.entity;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.os.javaee.orm.multitenancy.context.ITenantContextHolder;
import org.os.javaee.orm.multitenancy.context.TenantContext;
import org.os.javaee.orm.multitenancy.entity.MultiTenancyEnabledEntity;
import org.os.javaee.orm.multitenancy.hibernate.dao.MultiTenancyEnabledDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: MultiTenancyEnabledEntityTest</p>
 * <p><b>Description:</b> MultiTenancyEnabledEntityTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class MultiTenancyEnabledEntityTest {

	private static Logger log = Logger.getLogger(MultiTenancyEnabledEntityTest.class);

	static MultiTenancyEnabledDAO<MultiTenancyEnabledEntity> dao = null;
	static ITenantContextHolder tenantContextHolder = null;
	static Random random = null;
	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = ctx.getBean("mtEnabledDAO", MultiTenancyEnabledDAO.class);
		tenantContextHolder = ctx.getBean("tenantContextHolder", ITenantContextHolder.class);
		random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(System.currentTimeMillis());
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.os.javaee.orm.multitenancy.entity.MultiTenancyEnabledEntity} creation.
	 */
	@Test
	public final void createEntity() {
		TenantContext context = new TenantContext();
		context.setTenantId(String.valueOf(random.nextInt()));
		tenantContextHolder.setTenantContext(context);
		MultiTenancyEnabledEntity entity = new MultiTenancyEnabledEntity();
		entity.setName("OM SRI RAMA");
		dao.save(entity);
	}
	
	/**
	 * Test method for {@link org.os.javaee.orm.multitenancy.entity.MultiTenancyEnabledEntity} creation/read/updation/deletion.
	 */
	@Test
	public final void crudEntity() {
		TenantContext context = new TenantContext();
		int tenantId = random.nextInt();
		context.setTenantId(String.valueOf(tenantId));
		tenantContextHolder.setTenantContext(context);
		
		MultiTenancyEnabledEntity entity = new MultiTenancyEnabledEntity();
		entity.setName("OM SRI RAMA");
		
		Integer returnEntityId = (Integer)dao.save(entity);
		
		log.debug("Primary Key -->:"+(entity.getId()));
		Assert.assertNotNull(returnEntityId);
		Assert.assertNotNull(entity.getTenantId());
		Assert.assertEquals(tenantId,entity.getTenantId());
		
		MultiTenancyEnabledEntity getEntity = dao.get(MultiTenancyEnabledEntity.class, returnEntityId);
		
		Assert.assertNotNull(getEntity);
		Assert.assertNotNull(getEntity.getId());
		Assert.assertNotNull(getEntity.getTenantId());
		Assert.assertEquals(tenantId,getEntity.getTenantId());
		getEntity.setName("Jai Sri Ram");
		dao.update(getEntity);
		
		MultiTenancyEnabledEntity updatedEntity = dao.get(MultiTenancyEnabledEntity.class, returnEntityId);
		
		Assert.assertNotNull(updatedEntity);
		Assert.assertNotNull(updatedEntity.getId());
		Assert.assertNotNull(updatedEntity.getTenantId());
		//Assert.assertEquals(returnEntityId,updatedEntity.getId());
		Assert.assertEquals(tenantId,updatedEntity.getTenantId());
		Assert.assertEquals("Jai Sri Ram",updatedEntity.getName());
		
		dao.delete(entity);
		
	}	
}