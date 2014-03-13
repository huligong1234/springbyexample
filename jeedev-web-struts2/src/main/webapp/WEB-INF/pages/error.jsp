<%@ page language="java" import="java.util.List,java.util.Map,
org.jeedevframework.web.ActionBase,
org.jeedevframework.web.BufferedJspWriter,
static org.jeedevframework.web.Json.*;" pageEncoding="UTF-8"%>

<%! 
private void clean(javax.servlet.jsp.JspWriter w) throws java.io.IOException {
	((BufferedJspWriter)w).removeLast(",");
}
%>

<%
	JspWriter oldOut = out;
	BufferedJspWriter newOut = new BufferedJspWriter(1024*10,true);
	out = newOut;
	String msg="发生错误";
%>
	{
			"error": "false",
			"status": "no",
			"update": "true",
			"msg": <%=simple(msg)%>
	}
<%
	oldOut.print(out.toString());
	out = oldOut;
%>