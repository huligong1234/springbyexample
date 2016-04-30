package org.jeedevframework.app.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeedevframework.app.entity.App;
import org.jeedevframework.app.service.AppService;
import org.jeedevframework.core.common.entity.PageBean;
import org.jeedevframework.core.common.entity.QuerySort;
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
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("appCode", RequestUtil.getString(request, "appCode", ""));
		paramMap.put("appName", RequestUtil.getString(request, "appName", ""));
		
		PageBean pageBean = PageBean.getInstance(request);
		pageBean.setQueryParameter(paramMap);
		pageBean.getQuerySorts().add(new QuerySort("app_name",QuerySort.ORDER_ASC));
		pageBean = this.appService.selectListByCondition(pageBean);
		
		List<App> queryResult = (List<App>) pageBean.getQueryResult();
		if(null == queryResult){
			queryResult = Collections.emptyList();
		}

		this.resultMap.put("success", true);
		this.resultMap.put("total",0);
		this.resultMap.put("rows", queryResult);
		
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
