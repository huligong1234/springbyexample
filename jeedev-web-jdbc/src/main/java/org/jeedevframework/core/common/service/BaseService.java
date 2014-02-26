package org.jeedevframework.core.common.service;

import java.util.List;

public interface BaseService<T> {
	public int save(T obj);
	public int delete(T obj);
	public int update(T obj);
	public T findById(Integer id);
	public List<T> findList(T obj);
}
