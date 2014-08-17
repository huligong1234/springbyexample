package org.jeedevframework.core.common.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable primaryKey);
	public List<T> findList(T entity);
}
