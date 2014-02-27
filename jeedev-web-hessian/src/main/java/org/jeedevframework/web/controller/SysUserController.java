package org.jeedevframework.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeedevframework.web.domain.SysUser;
import org.jeedevframework.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sysUser/")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request,SysUser sysUser){
		int result = this.sysUserService.save(sysUser);
		return "success";
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public String update(HttpServletRequest request,SysUser sysUser){
		int result = this.sysUserService.update(sysUser);
		return "success";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(HttpServletRequest request,@PathVariable("id") Integer id){
		SysUser sysUserParam = new SysUser();
		sysUserParam.setId(id);
		int result = this.sysUserService.delete(sysUserParam);
		return "success";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public SysUser get(HttpServletRequest request,@PathVariable("id") Integer id){
		return this.sysUserService.findById(id);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<SysUser> list(HttpServletRequest request,ModelMap map){
		SysUser sysUserQ = new SysUser();
		List<SysUser> list = this.sysUserService.findList(sysUserQ);
		return  list;
	}
}
