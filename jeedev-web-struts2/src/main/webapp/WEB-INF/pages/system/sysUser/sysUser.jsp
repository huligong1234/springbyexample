<%@ page language="java" import="
java.util.List,
java.util.Map,
org.json.JSONObject,
org.json.JSONArray,
org.jeedevframework.web.util.*,
org.jeedevframework.web.domain.SysUser" 
pageEncoding="UTF-8"%>


<%
	SysUser sysUser = (SysUser)request.getAttribute("sysUser");

    JSONObject json = new JSONObject();

    JSONObject result = new JSONObject();
    json.put("error", "false");
    json.put("status", "ok");
    json.put("sysUser", result);
    
    result.put("id", sysUser.getId());
    result.put("username", sysUser.getUsername());
    result.put("password", sysUser.getPassword());

    ResponseUtil.renderJson(response, json.toString());
%>
