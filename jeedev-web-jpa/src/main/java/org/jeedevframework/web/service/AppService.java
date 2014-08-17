package org.jeedevframework.web.service;

import java.util.List;
import java.util.Map;

import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.core.common.service.BaseService;
import org.jeedevframework.web.domain.App;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AppService extends BaseService<App> {

	List<Map<String, Object>> findListByCondition(Map<String, String> paramMap, PageBean pageBean);

}
