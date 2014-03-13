package org.jeedevframework.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.Action;

public class CharacterEncodeFilter implements Filter {

	protected String encoding = "UTF-8";
	protected FilterConfig filterConfig = null;

	
	public CharacterEncodeFilter() {
		super();
		
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

	}
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		//HttpServletResponse resp = (HttpServletResponse)response;
		request.setCharacterEncoding(encoding);
		//HttpSession session = req.getSession(true);
		String contextPath =  req.getContextPath();
		String url =  req.getRequestURI();
		
		url=url.substring(contextPath.length());
		 chain.doFilter(request,response);
	}
		 

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}
	
	protected String selectEncoding(ServletRequest request) {
		  return (this.encoding);
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
	
}
