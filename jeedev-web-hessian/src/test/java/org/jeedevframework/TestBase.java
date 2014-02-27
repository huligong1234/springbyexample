package org.jeedevframework;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

/**
 * 单元测试抽象类，具体测试类需要使用Spring上下文时可继承此类
 * @author huligong
 * */
public abstract class TestBase {
	
	protected static ApplicationContext context = null;
	protected static JdbcTemplate jdbcTemplate;
	
	@BeforeClass
	public static void initContext(){
		context = new ClassPathXmlApplicationContext("applicationContext-dev.xml");
		Assert.notNull(context);
		jdbcTemplate = context.getBean("jdbcTemplate",JdbcTemplate.class);
		Assert.notNull(jdbcTemplate);
	}
	
	
	@AfterClass
	public static void detoryResource(){
	}
	
}
