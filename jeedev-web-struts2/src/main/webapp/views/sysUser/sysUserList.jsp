<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript" src="<%= request.getContextPath() %>/plug-in/jquery/jquery.min.js"></script>
  <body>
    <c:out value="${message}"></c:out>
    
<form id="listForm">
    <div class="mrightTop">
        <div class="fontl">
            <div id="searchParam">
				用户名：<input type="text" id="queryUsername" name="queryUsername"/>
            </div>
            <input class="formbtn" value="查询" type="button" onclick="execQuery()"/>
        </div>
    </div>
    <div class="mrightTop">
        <div class="fontr" id="pp"> </div>
    </div>
</form>

<div>
	<div class="tdare">
		<table width="100%" cellspacing="0" id="datas" class="dataTable">	
			<tr class="tatr1">
			 <td name="id">id</td>
			  <td name="username">username</td>
			  <td name="password">password</td>
			</tr>		
		</table>
	</div>
</div>
  </body>
</html>


<script type="text/javascript">
$(function () {
	initSearchForm();
	renderTable("");
  });

/**
 *init search form
 **/
function initSearchForm(){

}


function execQuery(){
	var username = $.trim($("#queryUsername").val());
	renderTable(username);
	
}

/**
 *render table to show data
 **/
function renderTable(username){
	 $.ajax({
         type: "POST",
         dataType:"json",
         url:"<%= request.getContextPath()%>/system/sysUser/find.do",		
         //async:false,
         data:{username:username},
         success:function(json) {
        	 insertSimpleTr('datas',json);
          }
      });
}


/**
 * add data to table
 *@param tbId
 *@param json
 **/
function insertSimpleTr(tbId,json){
	 $("#"+tbId+" tr:not(:first)").empty();
	 var result = json["result"];
	 if(result.length>0){		    
	    for(var i=0;i<result.length;i++){
	        var tr = $("#"+tbId+" tr").eq(0).clone();
			tr.appendTo("#"+tbId+"");
			tr.removeClass('tatr1');
			tr.addClass('tatr2');
			var td = $(tr).find('td');
			
			td.each(function(){
				var atrrName = $(this).attr("name");
				$(this).html('');
				if($(result[i]).attr(atrrName)){
					$(this).html($(result[i]).attr(atrrName));
				}						
			});
		}
	}
}
</script>