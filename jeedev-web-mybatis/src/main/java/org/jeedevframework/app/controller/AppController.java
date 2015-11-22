package org.jeedevframework.app.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeedevframework.app.model.App;
import org.jeedevframework.app.service.AppService;
import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.core.common.web.BaseController;
import org.jeedevframework.core.util.RequestUtil;
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
		//List<App> list = this.appService.selectAll();
		List<App> list = this.appService.selectAllUseCache();
		
		this.resultMap.put("success", true);
		this.resultMap.put("total", list.size());
		this.resultMap.put("rows", list);
		
		return this.resultMap;
	}
	
	
	@RequestMapping(value="/selectListByCondition",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request,ModelMap modelMap){
		Map<String,String> paramMap = new HashMap<String,String>();
		
		paramMap.put("appCode", RequestUtil.getString(request, "appCode", ""));
		paramMap.put("appName", RequestUtil.getString(request, "appName", ""));
		
		PageBean pageBean = PageBean.getInstance(request);
		pageBean.setParamData(paramMap);
		pageBean = this.appService.selectListByCondition(pageBean);
		
		List<App> list =  null;
		Object resultData = pageBean.getResultData();
		if(null != resultData){
			list = (List<App>)pageBean.getResultData();
		}else{
			list = Collections.emptyList();
		}

		this.resultMap.put("success", true);
		this.resultMap.put("total",0);
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
