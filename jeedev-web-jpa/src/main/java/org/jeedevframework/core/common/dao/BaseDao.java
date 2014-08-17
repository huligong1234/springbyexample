package org.jeedevframework.core.common.dao;

import java.io.Serializable;
import java.util.List;

import org.jeedevframework.core.common.domain.IDEntity;

public interface BaseDao<T extends IDEntity> {
	public void setMapperClass(Class<T> mapperClass);
	public void save(T entity);
	public void update(T entity);
	public void delete(T entity);
	public T findById(Serializable primaryKey);
	public List<T> findList(T entity);
}
