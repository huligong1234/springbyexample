package org.jeedevframework.web.dao.impl;

import java.util.List;

import org.jeedevframework.TestBase;
import org.jeedevframework.web.dao.SysUserDao;
import org.jeedevframework.web.domain.SysUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.Assert;

public class SysUserDaoImplTest extends TestBase {

	private SysUserDao sysUserDao;
	
	@Before
	public void setUp(){
		sysUserDao = context.getBean(SysUserDao.class);
		Assert.notNull(sysUserDao);
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void save(){
		SysUser sysUser = new SysUser("admin1","123456");
		int result = this.sysUserDao.save(sysUser);
		Assert.isTrue(result==1);
	}
	
	@Test
	public void update(){
		SysUser sysUser = new SysUser(1,"admin1","123456");
		int result = this.sysUserDao.update(sysUser);
		Assert.isTrue(result==1);
	}
	
	@Ignore
	@Test
	public void delete(){
		SysUser sysUser = new SysUser();
		sysUser.setId(2);
		int result = this.sysUserDao.delete(sysUser);
		Assert.isTrue(result==1);
	}
	
	@Test
	public void findById(){
		Integer id = 1;
		SysUser sysUser = this.sysUserDao.findById(id);
		Assert.notNull(sysUser);
		Assert.isTrue(id==sysUser.getId());
	}
	
	@Test
	public void findList(){
		SysUser sysUserQ = new SysUser();
		List<SysUser> list = this.sysUserDao.findList(sysUserQ);
		Assert.notEmpty(list);
		System.out.println(list);
	}
}
