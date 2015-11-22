package org.jeedevframework.core.util;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
	public static String renderJson(HttpServletResponse response, String content){  
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.setHeader("Cache-Control", "no-cache");
	    java.io.PrintWriter pw = null;  
	    try{  
	        pw = response.getWriter();  
	        pw.write(content);  
	    }catch (Exception e){  
	        e.printStackTrace();   
	    }finally{  
	        pw.close();  
	    }  
	    return null;  
	}  

}
