package org.jeedevframework.web.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.jeedevframework.web.ActionBase;
import org.jeedevframework.web.domain.SysUser;
import org.jeedevframework.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.Action;

@Scope("request")
@Component("sysUserAction")
public class SysUserAction  extends ActionBase {
	
	private static final long serialVersionUID = 1883571710001775118L;

	@Autowired
	private SysUserService sysUserService;
	private SysUser sysUser;
	private List<SysUser> resultList;
	
	   
	private String username;
	
	public String findById(){
		String result = Action.SUCCESS;
		HttpServletRequest request = ServletActionContext.getRequest();
		String  strId = request.getParameter("id");
		if(strId != null && strId.length()> 0){
			Integer id = Integer.valueOf(strId);
			sysUser = this.sysUserService.findById(id);
			return result;
		}else{
			result = Action.ERROR;
		}
		return result;
	}

	public String find() {
		if(StringUtils.isEmpty(username)){
			resultList = this.sysUserService.find();
		}else{
			resultList = this.sysUserService.findByUsername(username);
		}
		return Action.SUCCESS;
	}
	
	public String add() {
		return Action.SUCCESS;
	}
	
	public String update() {
		return Action.SUCCESS;
	}
	
	public String del() {
		return Action.SUCCESS;
	}
	
	
	
	public SysUserService getSysUserService() {
		return sysUserService;
	}
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public List<SysUser> getResultList() {
		return resultList;
	}
	public void setResultList(List<SysUser> resultList) {
		this.resultList = resultList;
	}
}
