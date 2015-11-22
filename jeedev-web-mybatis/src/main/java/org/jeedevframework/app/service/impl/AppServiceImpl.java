package org.jeedevframework.app.service.impl;

import java.util.List;

import org.jeedevframework.app.mapper.AppMapper;
import org.jeedevframework.app.model.App;
import org.jeedevframework.app.service.AppService;
import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.core.common.mapper.BaseMapper;
import org.jeedevframework.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@EnableCaching(proxyTargetClass = true) 
@Service
public class AppServiceImpl extends BaseServiceImpl<App,Integer> implements AppService {

	@Autowired
	public AppMapper appMapper;

    @Override
    public BaseMapper<App, Integer> getMapper() {
        return appMapper;
    }
    

	@Override
	public List<App> selectAllUseCache() {
		String cacheKey = "app_selectAllUseCache";
		Object cache = this.getCache(cacheKey);
		if(null != cache){
			return (List<App>)cache;
		}
		List<App>  list = this.selectAll();
		if(null != list){
			this.putCache(cacheKey, list);
		}
		return list;
	}


	@Override
	public PageBean selectListByCondition(PageBean pageBean) {
		return this.appMapper.selectListByCondition(pageBean);
	}

	
}
