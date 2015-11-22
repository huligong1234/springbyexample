package org.jeedevframework.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/sayHello")
	public String sayHello(HttpServletRequest request,ModelMap map){
		map.put("message", "Maven+Spring4+springMVC-MyBatis example");
		return "sayHello.jsp";
	}
}
