package org.jeedevframework.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * base class for all Actions.
 */
public class ActionBase extends ActionSupport implements Preparable, RequestAware, SessionAware {
	
	private static final long serialVersionUID = 1003729781210569108L;
	protected final Logger  logger =  Logger.getLogger(getClass());
    private Exception exception;
    private Map<String,Object> session;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void prepare() throws Exception {
    }

    public void setSession(Map<String,Object> session) {
        if(session==null){
            session = new HashMap<String,Object>();
        }
        this.session = session;
    }

    public Map<String,Object> getSession() {
        return session;
    }

    public void setRequest(Map<String,Object> arg0) {
        
    }
    public Logger getLogger() {
		return logger;
	}

	public String getParameter(String paramName){
		return ServletActionContext.getRequest().getParameter(paramName);
	}

}
