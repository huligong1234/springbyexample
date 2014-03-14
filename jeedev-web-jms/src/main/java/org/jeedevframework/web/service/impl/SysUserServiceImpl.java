package org.jeedevframework.web.service.impl;

import java.util.List;

import org.jeedevframework.web.dao.SysUserDao;
import org.jeedevframework.web.domain.SysUser;
import org.jeedevframework.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public int save(SysUser obj) {
		return this.sysUserDao.save(obj);
	}

	@Override
	public int delete(SysUser obj) {
		return this.sysUserDao.delete(obj);
	}

	@Override
	public int update(SysUser obj) {
		return this.sysUserDao.update(obj);
	}

	@Override
	public SysUser findById(Integer id) {
		return this.sysUserDao.findById(id);
	}

	@Override
	public List<SysUser> findList(SysUser obj) {
		return this.sysUserDao.findList(obj);
	}

}
