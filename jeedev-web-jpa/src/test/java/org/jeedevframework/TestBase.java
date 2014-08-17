package org.jeedevframework;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 单元测试抽象类，具体测试类需要使用Spring上下文时可继承此类
 * @author huligong
 * */
 @ContextConfiguration("classpath:applicationContext-dev.xml")
public class TestBase extends AbstractJUnit4SpringContextTests {
	
	@BeforeClass
	public static void initContext(){
	}
	
	
	@AfterClass
	public static void detoryResource(){
	}
	
}
