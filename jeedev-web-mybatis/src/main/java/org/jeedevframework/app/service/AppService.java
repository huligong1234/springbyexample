package org.jeedevframework.app.service;

import java.util.List;

import org.jeedevframework.app.model.App;
import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.core.common.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AppService extends BaseService<App,Integer> {

	List<App> selectAllUseCache();
	
	PageBean selectListByCondition(PageBean pageBean);

}
