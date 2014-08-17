package org.jeedevframework.core.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class RequestUtil {

	public static String getString(HttpServletRequest request,String paramName,String defaultVal){
		String val = request.getParameter(paramName);
		if(StringUtils.isEmpty(val)){
			return defaultVal;
		}
		return val.trim();
	}
	
	public static int getInt(HttpServletRequest request,String paramName,int defaultVal){
		String val = request.getParameter(paramName);
		if(StringUtils.isNumeric(val)){
			return Integer.valueOf(val);
		}
		return defaultVal;
	}
}
