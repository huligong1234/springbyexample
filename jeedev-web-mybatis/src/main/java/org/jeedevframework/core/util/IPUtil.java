package org.jeedevframework.core.util;

public class IPUtil {

	/**
	 * ip补全为15位
	 * @param ip
	 * @return
	 */
	public static String ipComplete(String ip){
		if(ip==null || "".equals(ip)){
    		return "";
    	}
    	String ips[]=ip.split("\\.");
    	String newIp="";
    	for(int i=0;i<ips.length;i++){
    		String ip1=ips[i];
    		if(ip1.length()==0){
    			ip1="000";
    			newIp+="."+ip1;
    			continue;
    		}
    		if(ip1.length()==1){
    			ip1="00"+ip1;
    			newIp+="."+ip1;
    			continue;
    		}
    		if(ip1.length()==2){
    			ip1="0"+ip1;
    			newIp+="."+ip1;
    			continue;
    		}
    		newIp+="."+ip1;
    	}
        if(newIp.length()>0){
        	newIp=newIp.substring(1);
        }
        return newIp;		
	}
}
