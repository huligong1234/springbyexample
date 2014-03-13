package org.jeedevframework.web.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.jeedevframework.web.ActionBase;
import org.jeedevframework.web.domain.SysUser;
import org.jeedevframework.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;

/**
 * use struts2-json-plugin（result name="success" type="json"）
 */
@Scope("request")
@Component("sysUserJSONAction")
public class SysUserJSONAction extends ActionBase {
	
	private static final long serialVersionUID = 1883571710001775118L;

	@Autowired
	private SysUserService sysUserService;
	private Map<String,Object> resultMap;
	
	public String find(){
		List<SysUser> list = this.sysUserService.find();
		resultMap = new HashMap<String,Object>();
		resultMap.put("error", "false");
		resultMap.put("status", "ok");
		resultMap.put("result", list);
		return Action.SUCCESS;
	}

	public String findById(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String  strId = request.getParameter("id");
		if(strId != null && strId.length()> 0){
			Integer id = Integer.valueOf(strId);
			
			SysUser sysUser = this.sysUserService.findById(id);
			resultMap = new HashMap<String,Object>();
			resultMap.put("error", "false");
			resultMap.put("status", "ok");
			resultMap.put("result", sysUser);
			return Action.SUCCESS;
		}else{
			return Action.ERROR;
		}
	}
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
}
