package org.jeedevframework.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.jeedevframework.web.dao.impl.SysUserDaoImpl;
import org.jeedevframework.web.jms.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jms/")
public class JMSController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserDaoImpl.class);
	@Autowired
	private MessageSender messageSender;
	
	@RequestMapping(value="/send",method=RequestMethod.GET)
	@ResponseBody
	public String send(HttpServletRequest request,@RequestParam("message") String message) throws IOException{
		logger.info("message");
		if(message != null && !message.isEmpty()){
            System.out.println(String.format("send message: %s", message));
            messageSender.send(message);
        }else{
        	return "fail,message is empty";
        }
		return "success";
	}
}
