package org.jeedevframework.web.dao.impl;

import java.util.List;

import org.jeedevframework.web.dao.SysUserDao;
import org.jeedevframework.web.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserDaoImpl implements SysUserDao {

	//private static Log logger = LogFactory.getLog(SysUserDaoImpl.class);
	private static final Logger logger = LoggerFactory.getLogger(SysUserDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(SysUser sysUser) {
		String sql = "INSERT INTO sys_user(username,password) VALUES (?,?)";
		Object[] args = new Object[]{sysUser.getUserName(),sysUser.getPassword()};
		return this.jdbcTemplate.update(sql, args);
	}

	@Override
	public int delete(SysUser sysUser) {
		String sql = "DELETE FROM sys_user WHERE id=?";
		return this.jdbcTemplate.update(sql, sysUser.getId());
	}

	@Override
	public int update(SysUser sysUser) {
		String sql = "UPDATE sys_user SET username=?,password=? WHERE id=?";
		Object[] args = new Object[]{sysUser.getUserName(),sysUser.getPassword(),sysUser.getId()};
		return this.jdbcTemplate.update(sql, args);
	}

	@Override
	public SysUser findById(Integer id) {
		try{
			String sql = "SELECT * FROM sys_user WHERE id=?";
			return this.jdbcTemplate.queryForObject(sql, 
					ParameterizedBeanPropertyRowMapper.newInstance(SysUser.class),id);
		}catch(EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SysUser> findList(SysUser sysUser) {
		logger.info("######info.....");
		logger.debug("######debug.....");
		logger.error("######error.....");
		String sql = "SELECT * FROM sys_user";
		return this.jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(SysUser.class));
	}

}
