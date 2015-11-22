package org.jeedevframework.core.common.mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T, ID extends Serializable> {
	
	int deleteByPrimaryKey(ID id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(ID id);

	int updateByPrimaryKey(T record);

	List<T> selectAll();
}
