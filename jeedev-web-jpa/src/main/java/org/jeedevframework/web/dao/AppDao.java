package org.jeedevframework.web.dao;

import java.util.List;
import java.util.Map;

import org.jeedevframework.core.common.dao.BaseDao;
import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.web.domain.App;

public interface AppDao extends BaseDao<App> {

	List<Map<String, Object>> findListByCondition(Map<String, String> paramMap, PageBean pageBean);

}
