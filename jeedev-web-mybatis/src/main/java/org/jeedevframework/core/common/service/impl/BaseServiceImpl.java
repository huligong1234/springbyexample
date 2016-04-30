package org.jeedevframework.core.common.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.jeedevframework.core.common.entity.PageBean;
import org.jeedevframework.core.common.mapper.BaseMapper;
import org.jeedevframework.core.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	public static final String CACHE_NAME = "serviceCache";
	
	@Autowired
	private CacheManager cacheManager;
	
	private Cache cache;
	
	@Override
    public abstract BaseMapper<T, ID> getMapper() ;
	

	@Override
	public int insert(T entity) {
		return this.getMapper().insert(entity);
	}

	@Override
	public int deleteByPrimaryKey(ID id) {
		return this.getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(T entity) {
		return this.getMapper().updateByPrimaryKey(entity);
	}

	@Override
	public T selectByPrimaryKey(ID primaryKey) {
		return this.getMapper().selectByPrimaryKey(primaryKey);
	}

	
	@Override
	public List<T> selectAll() {
		return this.getMapper().selectAll();
	}
	
	@Override
	public PageBean selectListByCondition(PageBean pageBean) {
		int count = this.getMapper().selectPageCount(pageBean);
		pageBean.setTotal(count);
		if(count == 0){
			pageBean.setQueryResult(Collections.emptyList());
			return pageBean;
		}
		
		List<?> queryResult = this.getMapper().selectPageListByCondition(pageBean);
		pageBean.setQueryResult(queryResult);
		return pageBean;
	}
	
	protected synchronized Cache getCache() {
		if(cache == null){
			cache = cacheManager.getCache(CACHE_NAME);
		}
		return cache;
	}


	protected void putCache(String cacheKey,Object cacheObject){
		 Assert.notNull(cacheKey);
		 Assert.notNull(cacheObject);
		 Element element = new Element(cacheKey, (Serializable) cacheObject);
		 
		 this.getCache().put(element);
	}
	
	protected void removeCache(String cacheKey){
		this.getCache().remove(cacheKey);
	}
	
	protected void removeAllCache(){
		this.getCache().removeAll();
	}
	
	protected Object getCache(String cacheKey){
		Assert.notNull(cacheKey);
		Element element = this.getCache().get(cacheKey);  
        if (element == null) {  
        	return null;
        }  
        Serializable cacheObject = element.getValue();  
        return cacheObject;
	}

}
