package org.os.javaee.orm.multitenanacy.entity;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.os.javaee.orm.multitenancy.context.ITenantContextHolder;
import org.os.javaee.orm.multitenancy.entity.IEntityManagerAdapter;
import org.os.javaee.orm.multitenancy.entity.SimpleEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: SchemaBasedMultiTenancyTest</p>
 * <p><b>Description:</b> SchemaBasedMultiTenancyTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class SchemaBasedMultiTenancyTest {
	private static Logger log = Logger.getLogger(MultiTenancyEnabledEntityTest.class);

	private static IEntityManagerAdapter adapter = null;
	static Random random = null;
	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-schema-config.xml");
		adapter = ctx.getBean("adapter", IEntityManagerAdapter.class);
		random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(System.currentTimeMillis());
	}

	@Test
	public final void verifyCreate() {
		SimpleEntity entity = new SimpleEntity();
		entity.setTenantId(random.nextInt());
		entity.setName("Murali Reddy");
		adapter.create(entity);
	}
}
