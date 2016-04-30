package org.jeedevframework.core.common.service;

import java.io.Serializable;
import java.util.List;

import org.jeedevframework.app.entity.App;
import org.jeedevframework.core.common.entity.PageBean;
import org.jeedevframework.core.common.mapper.BaseMapper;

public interface BaseService<T,ID extends Serializable> {
	
	BaseMapper<T, ID> getMapper();
	
    int deleteByPrimaryKey(ID id);

    int insert(T record);

    T selectByPrimaryKey(ID id);

    List<T> selectAll();
    
    int updateByPrimaryKey(T record);

	PageBean selectListByCondition(PageBean pageBean);

}
