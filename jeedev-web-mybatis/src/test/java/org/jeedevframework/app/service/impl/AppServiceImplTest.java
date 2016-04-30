package org.jeedevframework.app.service.impl;

import java.util.Date;
import java.util.List;

import org.jeedevframework.TestBase;
import org.jeedevframework.app.entity.App;
import org.jeedevframework.app.service.AppService;
import org.jeedevframework.core.common.entity.PageBean;
import org.jeedevframework.core.common.entity.QuerySort;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING )
@Rollback(true)
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
	@Ignore
	public void testA_insert(){
		App app = new App();
		app.setId(1);
		app.setAppCode("1000");
		app.setAppName("myapp1");
		app.setCreateDate(new Date());
		this.appService.insert(app);
	}
	
	@Test
	public void testB_selectByPrimaryKey(){
		App app = this.appService.selectByPrimaryKey(1);
		Assert.notNull(app);
		System.out.println(app);
	}
	
	@Ignore
	@Test
	public void testC_updateByPrimaryKey(){
		App app = this.appService.selectByPrimaryKey(1);
		System.out.println(app);
		
		app.setAppCode("1001");
		this.appService.updateByPrimaryKey(app);
		
		app = this.appService.selectByPrimaryKey(1);
		Assert.isTrue("1001".equals(app.getAppCode()));
		System.out.println(app);
	}
	
	
	@Test
	public void testD_selectAll(){
		List<App> list = this.appService.selectAll();
		Assert.notEmpty(list);
		System.out.println(list);
	}
	
	@Test
	public void testE_selectListByCondition(){
		PageBean pageBean = new PageBean(0,10);
		pageBean.getQueryParameter().put("appName", "逃亡"); //查询条件
		pageBean.getQuerySorts().add(new QuerySort("app_name",QuerySort.ORDER_DESC)); //排序
		pageBean.getQuerySorts().add(new QuerySort("id",QuerySort.ORDER_ASC));//排序
		
		pageBean = this.appService.selectListByCondition(pageBean);
		
		Assert.notNull(pageBean);
		Assert.notNull(pageBean.getQueryResult());
		System.out.println(pageBean.getQueryResult());
	}
	
	@Test
	//@Rollback(true)
	@Ignore
	public void testF_deleteByPrimaryKey(){
		this.appService.deleteByPrimaryKey(1);
		List<App> list = this.appService.selectAll();
		Assert.isTrue(list.isEmpty());
	}
}
