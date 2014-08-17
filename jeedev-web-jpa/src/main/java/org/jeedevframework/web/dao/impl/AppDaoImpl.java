package org.jeedevframework.web.dao.impl;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jeedevframework.core.common.dao.impl.BaseDaoImpl;
import org.jeedevframework.core.common.domain.PageBean;
import org.jeedevframework.core.util.StringPool;
import org.jeedevframework.web.dao.AppDao;
import org.jeedevframework.web.domain.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class AppDaoImpl extends BaseDaoImpl<App> implements AppDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public AppDaoImpl() {
		super();
		this.setMapperClass(App.class);
	}

	@Override
	public List<Map<String,Object>> findListByCondition(Map<String,String> paramMap,PageBean pageBean){
		StringBuffer sqlSb = new StringBuffer(200);
		sqlSb.append(" SELECT * FROM app");
		
		StringBuffer sbWhere = new StringBuffer();
		sbWhere.append(" WHERE app.is_delete=0 ");
		sbWhere.append(StringUtils.isEmpty(paramMap.get("app_name")) ? StringPool.BLANK : MessageFormat.format(" AND app.app_name REGEXP ''{0}''",paramMap.get("app_name")));
		
		
		StringBuffer sqlCountSb = new StringBuffer(100);
		sqlCountSb.append(" SELECT COUNT(0) FROM app");
		sqlCountSb.append(sbWhere.toString());
		String sqlTotal = sqlCountSb.toString();
		int total = this.jdbcTemplate.queryForObject(sqlTotal, Integer.class);
		pageBean.setTotal(total);
		
		if(total==0){
			return Collections.emptyList();
		}
		
		sqlSb.append(sbWhere.toString());
		sqlSb.append(" ORDER BY ").append(StringUtils.isEmpty(paramMap.get("sort"))	?	"app.re_order" : paramMap.get("sort"))
			 .append(" ").append(StringUtils.isEmpty(paramMap.get("order"))	?	"DESC" : paramMap.get("order"));
		sqlSb.append(" LIMIT ").append(pageBean.getOffset()).append(" , ").append(pageBean.getRows());
		
		String sql = sqlSb.toString();
		
		List<Map<String,Object>> resultList = this.jdbcTemplate.queryForList(sql);
		if((resultList == null) || resultList.isEmpty()){
			return Collections.emptyList();
		}
		return resultList;		
	}
}
