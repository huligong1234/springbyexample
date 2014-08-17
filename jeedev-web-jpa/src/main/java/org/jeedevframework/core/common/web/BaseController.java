package org.jeedevframework.core.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeedevframework.core.util.IPUtil;

public class BaseController {

	public final static String REQ_SUFFIX = ".htm";
	
	protected Map<String,Object> resultMap = new HashMap<String,Object>();
	
	//获取客户机IP地址
	public String getIpAddr(HttpServletRequest request) {  
	       String ip = request.getHeader("x-real-ip");   
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("Proxy-Client-IP");      
	       }
	       
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("x-forwarded-for");      
	       }
	     
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("WL-Proxy-Client-IP");      
	       } 
	      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	       }    
	     
	       return IPUtil.ipComplete(ip);      
	 }

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
}
