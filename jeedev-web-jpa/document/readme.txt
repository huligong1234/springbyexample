Maven+SpringMVC3.2+JPA+Ehcache+Junit4

======================
jar download:
http://search.maven.org/

======================
druid doc

JPA注解参考文档
http://fenglyn.iteye.com/blog/844304

spring cache
http://jinnianshilongnian.iteye.com/blog/2001040

ehcache
http://www.ehcache.org/documentation

======================

http://localhost:8080/jeedev-web-jpa/sayHello.htm

http://localhost:8080/jeedev-web-jpa/rest/app/list

http://localhost:8080/jeedev-web-jpa/rest/app/findListByCondition



==============================
jpa配置使用
1.pom.xml
2./src/main/resources/applicationContext-jdbc.xml,jdbc-dev.properties
3./src/main/resources/META-INF/persistence.xml
4.org.jeedevframework.web.domain。App (extends org.jeedevframework.core.common.domain.IDEntity)
5.org.jeedevframework.web.dao.impl.AppDaoImpl (extends org.jeedevframework.core.common.dao.impl。BaseDaoImpl)
6./src/main/resources/ehcache.xml


======================
cmd:

export db:
mysqldump  -u root -p123456 jeedev > d:\jeedev.sql

import db:
mysql -uroot -p123456 jeedev < d:\jeedev.sql



当前示例中，有三种基于ehcache的缓存实现
1.一是基于JPA的Cache实现，详细见 org.jeedevframework.core.common.dao.impl.BaseDaoImpl query.setHint("org.hibernate.cacheable", true);
2.二是基于注解的Cache实现, 详细见 org.jeedevframework.web.service.impl.AppServiceImpl，@EnableCaching @Cacheable
3.三是手工Cache实现, 详细见 org.jeedevframework.web.service.impl.AppServiceImpl及org.jeedevframework.core.common.service.impl.BaseServiceImpl


ehcache.xml config
1.必须要有的属性：
name: cache的名字，用来识别不同的cache，必须惟一。
maxElementsInMemory: 内存管理的缓存元素数量最大限值。
maxElementsOnDisk: 硬盘管理的缓存元素数量最大限值。默认值为0，就是没有限制。
eternal: 设定元素是否持久话。若设为true，则缓存元素不会过期。
overflowToDisk: 设定是否在内存填满的时候把数据转到磁盘上。
2.下面是一些可选属性：
timeToIdleSeconds： 设定元素在过期前空闲状态的时间，只对非持久性缓存对象有效。默认值为0,值为0意味着元素可以闲置至无限长时间。
timeToLiveSeconds: 设定元素从创建到过期的时间。其他与timeToIdleSeconds类似。
diskPersistent: 设定在虚拟机重启时是否进行磁盘存储，默认为false.(我的直觉，对于安全小型应用，宜设为true)。
diskExpiryThreadIntervalSeconds: 访问磁盘线程活动时间。
diskSpoolBufferSizeMB: 存入磁盘时的缓冲区大小，默认30MB,每个缓存都有自己的缓冲区。
memoryStoreEvictionPolicy: 元素逐出缓存规则。共有三种，Recently Used (LRU)最近最少使用，为默认。 First In First Out (FIFO)，先进先出。Less Frequently Used(specified as LFU)最少使用