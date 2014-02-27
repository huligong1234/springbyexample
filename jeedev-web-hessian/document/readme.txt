Maven+SpringMVC3.2+SpringJDBC+Junit4+Druid
Hessian4.0.33

======================
jar download:

http://search.maven.org/

=========reference doc=============
druid doc
http://git.oschina.net/wenshao/druid


spring-hessian
http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/remoting.html

http://oss.org.cn/ossdocs/framework/spring/zh-cn/remoting.html

http://www.cnblogs.com/yangwn/archive/2010/07/31/1789213.html


=========validate=============

http://localhost:8080/jeedev-web-hessian/sayHello.htm
http://localhost:8080/jeedev-web-hessian/rest/sysUser/list

hessian test
http://localhost:8080/jeedev-web-hessian/hessian/userService

or
run org.jeedevframework.web.hessian.InvokeApp


==========db install============
cmd:

export db:
mysqldump  -u root -p123456 jeedev > d:\jeedev.sql

import db:
mysql -uroot -p123456 jeedev < d:\jeedev.sql