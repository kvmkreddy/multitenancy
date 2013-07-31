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
import org.os.javaee.orm.multitenancy.context.CompositeTenantContext;
import org.os.javaee.orm.multitenancy.context.CompositeTenantInfo;
import org.os.javaee.orm.multitenancy.context.ITenantContext;
import org.os.javaee.orm.multitenancy.context.ITenantContextHolder;
import org.os.javaee.orm.multitenancy.context.TenantContext;
import org.os.javaee.orm.multitenancy.entity.CompositeMultiTenancyEnabledEntity;
import org.os.javaee.orm.multitenancy.entity.MultiTenancyEnabledDAO;
import org.os.javaee.orm.multitenancy.entity.MultiTenancyEnabledEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: CompositeMultiTenancyEnabledEntityTest</p>
 * <p><b>Description:</b> CompositeMultiTenancyEnabledEntityTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class CompositeMultiTenancyEnabledEntityTest {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(CompositeMultiTenancyEnabledEntityTest.class);

	static MultiTenancyEnabledDAO<CompositeMultiTenancyEnabledEntity> dao = null;
	static ITenantContextHolder tenantContextHolder = null;
	static Random random = null;
	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-compositeconfig.xml");
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
	 * Test method for {@link org.os.javaee.orm.multitenancy.entity.CompositeMultiTenancyEnabledEntity} creation.
	 */
	@Test
	public final void createEntity() {
		CompositeTenantInfo tenantInfo = new CompositeTenantInfo();
		ITenantContext<CompositeTenantInfo> context = getTenantContext(tenantInfo);
		tenantContextHolder.setTenantContext(context); 
		CompositeMultiTenancyEnabledEntity entity = new CompositeMultiTenancyEnabledEntity();
		entity.setName("Murali Reddy");
		dao.save(entity);
	}
	
	@Test
	public final void verifyFindAllNRead() {
		CompositeTenantInfo tenantInfo = new CompositeTenantInfo();
		ITenantContext<CompositeTenantInfo> context = getTenantContext(tenantInfo);
		tenantContextHolder.setTenantContext(context);
		
		CompositeMultiTenancyEnabledEntity entity = new CompositeMultiTenancyEnabledEntity();
		entity.setName("Murali Reddy");
		dao.create(entity);
		Assert.assertNotNull(entity);
		assertTenantInfo(tenantInfo, entity);
		
		List<CompositeMultiTenancyEnabledEntity> entityList = dao.findAll(CompositeMultiTenancyEnabledEntity.class);
		log.info("Entity List -->:"+(entityList));
		Assert.assertNotNull(entityList);
		Assert.assertEquals(entityList.size(), 1);
		Assert.assertEquals(entityList.get(0).getName(), "Murali Reddy");
		

		CompositeMultiTenancyEnabledEntity getEntity = dao.read(CompositeMultiTenancyEnabledEntity.class,entityList.get(0).getId());
		Assert.assertNotNull(getEntity);
		log.info("Entity Info -->:"+(getEntity));
		assertTenantInfo(tenantInfo, getEntity);
	}


	
	/**
	 * Test method for {@link org.os.javaee.orm.multitenancy.entity.CompositeMultiTenancyEnabledEntity} creation/read/updation/deletion.
	 */
	@Test
	public final void verifyCRUD() {
		CompositeTenantInfo tenantInfo = new CompositeTenantInfo();
		ITenantContext<CompositeTenantInfo> context = getTenantContext(tenantInfo);
		tenantContextHolder.setTenantContext(context);
		
		CompositeMultiTenancyEnabledEntity entity = new CompositeMultiTenancyEnabledEntity();
		entity.setName("Murali Reddy");
		
		Integer returnEntityId = (Integer)dao.save(entity);
		
		log.debug("Primary Key -->:"+(entity.getId()));
		Assert.assertNotNull(returnEntityId);
		assertTenantInfo(tenantInfo, entity);
		
		CompositeMultiTenancyEnabledEntity getEntity = dao.read(CompositeMultiTenancyEnabledEntity.class, returnEntityId);
		
		Assert.assertNotNull(getEntity);
		Assert.assertNotNull(getEntity.getId());
		assertTenantInfo(tenantInfo, getEntity);
		
		getEntity.setName("Murali Krishna Reddy");
		dao.update(getEntity);
		
		CompositeMultiTenancyEnabledEntity updatedEntity = dao.read(CompositeMultiTenancyEnabledEntity.class, returnEntityId);
		
		Assert.assertNotNull(updatedEntity);
		Assert.assertNotNull(updatedEntity.getId());
		assertTenantInfo(tenantInfo, updatedEntity);
		Assert.assertEquals("Murali Krishna Reddy",updatedEntity.getName());
		
		dao.delete(entity);
	}

	@Test
	public void verifyNamedQuery(){
		CompositeTenantInfo tenantInfo = new CompositeTenantInfo();
		ITenantContext<CompositeTenantInfo> context = getTenantContext(tenantInfo);
		tenantContextHolder.setTenantContext(context);
		
		CompositeMultiTenancyEnabledEntity entity = new CompositeMultiTenancyEnabledEntity();
		String name = "Murali Reddy ~ "+(tenantInfo.getTenantIdOne());
		entity.setName(name);
		
		Integer returnEntityId = (Integer)dao.save(entity);
		
		log.debug("Primary Key -->:"+(entity.getId()));
		Assert.assertNotNull(returnEntityId);
		assertTenantInfo(tenantInfo, entity);
		
		List<CompositeMultiTenancyEnabledEntity> entityList = dao.getListByNamedQuery("findAllCompositeMTEntitiesQuery");
		log.info("Entity List -->:"+(entityList));
		Assert.assertNotNull(entityList);
		Assert.assertEquals(entityList.size(), 1);
		Assert.assertEquals(entityList.get(0).getName(), name);
	}	

	private ITenantContext<CompositeTenantInfo> getTenantContext(CompositeTenantInfo tenantInfo) {
		ITenantContext<CompositeTenantInfo> context = new CompositeTenantContext();
		tenantInfo.setTenantIdOne(random.nextInt());
		tenantInfo.setTenantIdTwo(random.nextInt());
		tenantInfo.setTenantIdThree(random.nextInt());
		context.setTenantInfo(tenantInfo);
		return context;
	}

	private void assertTenantInfo(CompositeTenantInfo tenantInfo, CompositeMultiTenancyEnabledEntity entity) {
		Assert.assertNotNull(entity.getMtInfo().getTenantIdOne());
		Assert.assertNotNull(entity.getMtInfo().getTenantIdTwo());
		Assert.assertNotNull(entity.getMtInfo().getTenantIdThree());
		Assert.assertEquals(tenantInfo.getTenantIdOne(),entity.getMtInfo().getTenantIdOne());
		Assert.assertEquals(tenantInfo.getTenantIdTwo(),entity.getMtInfo().getTenantIdTwo());
		Assert.assertEquals(tenantInfo.getTenantIdThree(),entity.getMtInfo().getTenantIdThree());
	}
}