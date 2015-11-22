Maven+Spring4+SpringMVC+MyBatis3
===============

#### 一、概述
提供基于Spring4+SpringMVC+MyBatis3的集成使用示例
* 1.支持Ehcache
* 2.支持分页查询
* 3.使用Druid数据连接池
* 4.提供Mapper公共类BaseMapper和model公共父类IDEntity


#### 二、使用MyBatis提供的自动生成Mapper类和sqlMapper配置文件
```shell
mvn mybatis-generator:generate
```
不过由于定义了父类IDEntity和BaseMapper类，需要将自动生成的类修改下。

#### 三、通过Maven方式编译运行

```shell
mvn clean compile
mvn -Djetty.port=9999 jetty:run
```