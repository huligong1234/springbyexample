package org.jeedevframework.web.service.impl;

import java.util.Date;
import java.util.List;

import org.jeedevframework.TestBase;
import org.jeedevframework.web.domain.App;
import org.jeedevframework.web.service.AppService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class AppServiceImplTest extends TestBase {

	@Autowired
	private AppService appService;
	
	@Before
	public void setUp(){
		Assert.notNull(appService);
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void save(){
		App app = new App();
		app.setAppCode("1000");
		app.setAppName("myapp1");
		app.setPublishDate(new Date());
		this.appService.save(app);
	}
	
	@Ignore
	@Test
	public void update(){
		App app = this.appService.findById(1);
		System.out.println(app);
		
		app.setAppName("myappq");
		this.appService.update(app);
		
		app = this.appService.findById(1);
		System.out.println(app);
	}
	
	@Test
	//@Rollback(true)
	public void delete(){
		//App app = this.appDao.findById(1);
		
		//this.appDao.delete(app);
	}
	
	@Test
	public void findById(){
		App app = this.appService.findById(1);
		System.out.println(app);
	}
	
	@Test
	public void findList(){
		App app = new App();
		List<App> list = this.appService.findList(app);
		System.out.println(list);
	}
}
