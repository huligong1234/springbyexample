package org.jeedevframework.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.core.common.web.BaseController;
import org.jeedevframework.web.domain.App;
import org.jeedevframework.web.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/app")
@Controller
public class AppController extends BaseController {

	@Autowired
	private AppService appService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,ModelMap modelMap,PageBean pageBean){
		App entity = new App();
		List<App> list = this.appService.findList(entity);
		
		this.resultMap.put("success", true);
		this.resultMap.put("total", list.size());
		this.resultMap.put("rows", list);
		
		return this.resultMap;
	}
	
	@RequestMapping(value="/findListByCondition",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,ModelMap modelMap){
		Map<String,String> paramMap = new HashMap<String,String>();
		PageBean pageBean = PageBean.getInstance(request);
		List<Map<String,Object>> list = this.appService.findListByCondition(paramMap, pageBean);
		
		this.resultMap.put("success", true);
		this.resultMap.put("total", list.size());
		this.resultMap.put("rows", list);
		
		return this.resultMap;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(HttpServletRequest request,ModelMap modelMap,App app){
		System.out.println(app);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public void update(HttpServletRequest request,ModelMap modelMap,App app){
		System.out.println(app);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public void delete(HttpServletRequest request,ModelMap modelMap){
		
	}
}
