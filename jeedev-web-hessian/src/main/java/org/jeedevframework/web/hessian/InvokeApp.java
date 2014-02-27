package org.jeedevframework.web.hessian;

import java.net.MalformedURLException;
import java.util.List;

import org.jeedevframework.web.domain.SysUser;
import org.jeedevframework.web.service.SysUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.caucho.hessian.client.HessianProxyFactory;

public class InvokeApp {
	
	public static void main(String[] args) throws Exception {  
		
		ApplicationContext context = new ClassPathXmlApplicationContext("hessian-client.xml");
		SysUserService service = context.getBean("userServiceImpl", SysUserService.class);
       
		/*HessianProxyFactory proxyFactory = new HessianProxyFactory(); 
        String userHessianUrl = "http://localhost:8080/jeedev-web-hessian/hessian/userService";
        SysUserService service = (SysUserService)proxyFactory.create(SysUserService.class, userHessianUrl);
        */
		
        SysUser sysUserQ = new SysUser();
        List<SysUser> sysUserList = service.findList(sysUserQ);
        System.out.println(sysUserList);  
        System.out.println("hessian service invoke ok!");
    }  
}
