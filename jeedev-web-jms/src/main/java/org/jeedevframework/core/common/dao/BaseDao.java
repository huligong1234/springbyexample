package org.jeedevframework.core.common.dao;

import java.util.List;

public interface BaseDao<T> {
	public int save(T obj);
	public int delete(T obj);
	public int update(T obj);
	public T findById(Integer id);
	public List<T> findList(T obj);
}
