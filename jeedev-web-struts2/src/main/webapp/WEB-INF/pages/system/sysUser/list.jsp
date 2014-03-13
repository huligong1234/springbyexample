<%@ page language="java" import="
java.util.List,
java.util.Map,
org.json.JSONObject,
org.json.JSONArray,
org.jeedevframework.web.util.*,
org.jeedevframework.web.domain.SysUser" 
pageEncoding="UTF-8"%>


<%
    List<SysUser> resultList = (List<SysUser>)request.getAttribute("resultList");

    JSONObject json = new JSONObject();
    JSONArray result = new JSONArray();

    json.put("error", "false");
    json.put("status", "ok");
    json.put("result", result);

    if(null!=resultList && !resultList.isEmpty()){
        for (SysUser obj : resultList) {
            JSONObject jsonRow = new JSONObject();
            jsonRow.put("id", obj.getId());
            jsonRow.put("username", obj.getUsername());
            jsonRow.put("password", obj.getPassword());
            result.put(jsonRow);
        }
    }

    ResponseUtil.renderJson(response, json.toString());
%>
