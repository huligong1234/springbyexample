package org.jeedevframework.web.hessian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.caucho.HessianServiceExporter;


public class JeedevHessianServiceExporter extends HessianServiceExporter {
	private String ips; 
    @Override  
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	
    	String ip = getIpAddr(request);
    	String[] dips = ips.split("\\,");
    	boolean unlega = true;//表示不合法的请求
    	for(String dip : dips){
    		if(ip.startsWith(dip)){
    			unlega = false;
    			break;
    		}
    	}
        if(unlega && !"127.0.0.1".equals(ip)){
            //记录异常日志  
            return ;  
        }  
        super.handleRequest(request, response);  
    }  
  //获取客户机IP地址
  	private String getIpAddr(HttpServletRequest request) {  
  	       String ip = request.getHeader("x-forwarded-for");   
  	       
  	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
  	           ip = request.getHeader("Proxy-Client-IP");      
  	       }
  	       
  	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
  	           ip = request.getHeader("x-real-ip");      
  	       }
  	     
  	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
  	           ip = request.getHeader("WL-Proxy-Client-IP");      
  	       } 
  	      
  	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
  	           ip = request.getRemoteAddr();      
  	       }    
  	     
  	       return ip;//StringUtil.ipComplete(ip);      
  	 }
	public String getIps() {
		return ips;
	}
	public void setIps(String ips) {
		this.ips = ips;
	}
}