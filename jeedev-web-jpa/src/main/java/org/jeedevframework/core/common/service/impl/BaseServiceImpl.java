package org.jeedevframework.core.common.service.impl;

import java.io.Serializable;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.jeedevframework.core.common.dao.BaseDao;
import org.jeedevframework.core.common.domain.IDEntity;
import org.jeedevframework.core.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class BaseServiceImpl<T extends IDEntity> implements BaseService<T> {

	public static final String CACHE_NAME = "serviceCache";
	
	@Autowired
	private BaseDao<T> baseDao;
	
	@Autowired
	private CacheManager cacheManager;
	
	private Cache cache;
	

	@Override
	public void save(T entity) {
		this.baseDao.save(entity);
	}

	@Override
	public void delete(T entity) {
		this.baseDao.delete(entity);
	}

	@Override
	public void update(T entity) {
		this.baseDao.update(entity);
	}

	@Override
	public T findById(Serializable primaryKey) {
		return this.baseDao.findById(primaryKey);
	}

	@Override
	public List<T> findList(T entity) {
		return this.baseDao.findList(entity);
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
