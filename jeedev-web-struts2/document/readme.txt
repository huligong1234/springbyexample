Spring3 + Struts2 + JPA

========================================

dev-note
1./src/main/resources/META-INF/persistence.xml
2./src/main/resources/struts/struts-system.xml



http://localhost:8080/jeedev-web-struts2/system/sysUser/find.do

http://localhost:8080/jeedev-web-struts2/system/sysUser/findById.do?id=1


当有Spring的bean在Action内部时，要非常小心处理一个问题：
在action里面不能包含有接口的get方法

下面关于struts2和ajax的结合几点建议：
1、在页面用不到的数据最好不要传到前台（这也是之所以推荐第二种方法的原因，传的数据越大，效率越低不是吗！）
2、不是向前台传数据的方法最好不要以get开头，json插件会把所有get开头的方法当做属性，转为json格式数据
3、如果方法必须以get开头，然而又不是为了转为json格式，那么可以在该方法上加注解：@JSON(serialize=false)
@JSON(serialize=false)
public Set getUsers() {
    return users;
}
4、需要传到前台的数据，一定要在dao中加载完毕，不能使用懒加载模式。