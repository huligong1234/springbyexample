package org.jeedevframework.web.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.ProgressListener;
import org.jeedevframework.web.Constant;

public class EmiceProgressListener implements ProgressListener {
	private HttpServletRequest request = null;
	private String uuid;
	
	public EmiceProgressListener(HttpServletRequest request,String uuid){
		this.request=request;  
		this.uuid = uuid;
	}
   /**
    * @param pBytesRead  已下载文件大小 单位(Byte)
    * @param pContentLength  总文件大小 单位(Byte)
    * @param pItems 当前正在下载文件序列号
    */
	public void update(long pBytesRead, long pContentLength, int pItems){
		request.getSession().setAttribute("progress",pBytesRead+","+pContentLength+","+pItems);
		Constant.fileTransferStatusStore.put(uuid,pBytesRead+","+pContentLength+","+pItems);
	}
}
