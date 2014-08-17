package org.jeedevframework.web.service.impl;

import java.util.List;
import java.util.Map;

import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.core.common.service.impl.BaseServiceImpl;
import org.jeedevframework.web.dao.AppDao;
import org.jeedevframework.web.domain.App;
import org.jeedevframework.web.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@EnableCaching(proxyTargetClass = true) 
@Service
public class AppServiceImpl extends BaseServiceImpl<App> implements AppService {

	@Autowired
	private AppDao appDao;
	
	/**
	 * 基于注解的缓存处理
	 * */
	@Cacheable(value = "serviceCache",key="#paramMap") 
	@Override
	public List<Map<String,Object>> findListByCondition(Map<String,String> paramMap,PageBean pageBean){
		List<Map<String,Object>> list = this.appDao.findListByCondition(paramMap, pageBean);
		return list;
	}
	
	
	/**
	 * 手动处理缓存
	 * */
	public List<Map<String,Object>> findListByConditionFromCache(Map<String,String> paramMap,PageBean pageBean){
		String cacheKey = "findListByCondition";
		List<Map<String,Object>> list =  (List<Map<String, Object>>) getCache(cacheKey);
		if(list == null){
			list = this.appDao.findListByCondition(paramMap, pageBean);
			putCache(cacheKey, list);
		}
		
		return list;
	}
	
}
