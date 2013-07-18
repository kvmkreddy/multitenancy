package org.os.javaee.orm.multitenanacy.entity;

import java.security.SecureRandom;
import java.util.List;
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
import org.os.javaee.orm.multitenancy.entity.MultiTenancyEnabledDAO;
import org.os.javaee.orm.multitenancy.entity.MultiTenancyEnabledEntity;

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
	public final void verifyCreate() {
		TenantContext context = new TenantContext();
		context.setTenantInfo(String.valueOf(random.nextInt()));
		tenantContextHolder.setTenantContext(context);
		MultiTenancyEnabledEntity entity = new MultiTenancyEnabledEntity();
		entity.setName("Murali Reddy");
		dao.create(entity);
	}
	
	@Test
	public final void verifyFindAllNRead() {
		TenantContext context = new TenantContext();
		Integer tenantId = random.nextInt();
		context.setTenantInfo(String.valueOf(tenantId));
		tenantContextHolder.setTenantContext(context);
		
		MultiTenancyEnabledEntity entity = new MultiTenancyEnabledEntity();
		entity.setName("Murali Reddy");
		dao.create(entity);

		List<MultiTenancyEnabledEntity> entityList = dao.findAll(MultiTenancyEnabledEntity.class);
		log.info("Entity List -->:"+(entityList));
		Assert.assertNotNull(entityList);
		Assert.assertEquals(entityList.size(), 1);
		Assert.assertEquals(entityList.get(0).getName(), "Murali Reddy");
		

		MultiTenancyEnabledEntity getEntity = dao.read(MultiTenancyEnabledEntity.class,entityList.get(0).getId());
		Assert.assertNotNull(getEntity);
		log.info("Entity Info -->:"+(getEntity));
	}
	
	/**
	 * Test method for {@link org.os.javaee.orm.multitenancy.entity.MultiTenancyEnabledEntity} creation/read/updation/deletion.
	 */
	@Test
	public final void verifyCRUD() {
		TenantContext context = new TenantContext();
		int tenantId = random.nextInt();
		context.setTenantInfo(String.valueOf(tenantId));
		tenantContextHolder.setTenantContext(context);
		
		MultiTenancyEnabledEntity entity = new MultiTenancyEnabledEntity();
		entity.setName("Murali Reddy");
		
		Integer returnEntityId = (Integer)dao.save(entity);
		
		log.debug("Primary Key -->:"+(entity.getId()));
		Assert.assertNotNull(returnEntityId);
		Assert.assertNotNull(entity.getTenantId());
		Assert.assertEquals(tenantId,entity.getTenantId());
		
		MultiTenancyEnabledEntity getEntity = dao.read(MultiTenancyEnabledEntity.class, returnEntityId);
		
		Assert.assertNotNull(getEntity);
		Assert.assertNotNull(getEntity.getId());
		Assert.assertNotNull(getEntity.getTenantId());
		Assert.assertEquals(tenantId,getEntity.getTenantId());
		getEntity.setName("Murali Krishna Reddy");
		dao.update(getEntity);
		
		MultiTenancyEnabledEntity updatedEntity = dao.read(MultiTenancyEnabledEntity.class, returnEntityId);
		
		Assert.assertNotNull(updatedEntity);
		Assert.assertNotNull(updatedEntity.getId());
		Assert.assertNotNull(updatedEntity.getTenantId());
		//Assert.assertEquals(returnEntityId,updatedEntity.getId());
		Assert.assertEquals(tenantId,updatedEntity.getTenantId());
		Assert.assertEquals("Murali Krishna Reddy",updatedEntity.getName());
		
		dao.delete(entity);
	}
	
	@Test
	public void verifyNamedQuery(){
		TenantContext context = new TenantContext();
		int tenantId = random.nextInt();
		context.setTenantInfo(String.valueOf(tenantId));
		tenantContextHolder.setTenantContext(context);
		
		MultiTenancyEnabledEntity entity = new MultiTenancyEnabledEntity();
		String name = "Murali Reddy ~ "+(tenantId);
		entity.setName(name);
		
		Integer returnEntityId = (Integer)dao.save(entity);
		
		log.debug("Primary Key -->:"+(entity.getId()));
		Assert.assertNotNull(returnEntityId);
		Assert.assertNotNull(entity.getTenantId());
		Assert.assertEquals(tenantId,entity.getTenantId());
		
		List<MultiTenancyEnabledEntity> entityList = dao.getListByNamedQuery("findAllEntitiesQuery");
		log.info("Entity List -->:"+(entityList));
		Assert.assertNotNull(entityList);
		Assert.assertEquals(entityList.size(), 1);
		Assert.assertEquals(entityList.get(0).getName(), name);
	}
	
	@Test
	public void verifyNativeQuery(){
		TenantContext context = new TenantContext();
		int tenantId = random.nextInt();
		context.setTenantInfo(String.valueOf(tenantId));
		tenantContextHolder.setTenantContext(context);
		
		MultiTenancyEnabledEntity entity = new MultiTenancyEnabledEntity();
		String name = "Murali Reddy ~ "+(tenantId);
		entity.setName(name);
		
		Integer returnEntityId = (Integer)dao.save(entity);
		
		log.debug("Primary Key -->:"+(entity.getId()));
		Assert.assertNotNull(returnEntityId);
		Assert.assertNotNull(entity.getTenantId());
		Assert.assertEquals(tenantId,entity.getTenantId());
		
		List<MultiTenancyEnabledEntity> entityList = dao.getListByNamedNativeQuery("SELECT * FROM CLIENT");
		log.info("Entity List -->:"+(entityList));
		Assert.assertNotNull(entityList);
		//Assert.assertEquals(entityList.size(), 1);
		//Assert.assertEquals(entityList.get(0).getName(), name);
	}
	
}