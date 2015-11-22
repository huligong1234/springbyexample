package org.jeedevframework.app.mapper;

import java.io.Serializable;

import org.jeedevframework.app.model.App;
import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.core.common.mapper.BaseMapper;

public interface AppMapper extends BaseMapper<App, Integer> {

	PageBean selectListByCondition(PageBean pageBean);
	
}