# api-server-seed
API服务基础脚手架搭建，采用spring-boot\spring-session\mybatis\redis\quartz等，支持集群部署

[v2 is developing](https://github.com/imloama/api-server-seed/blob/v2)



## 更新纪录：

待实现
1. 集成spring-security
2. 集成swagger2
3. 继承基础controller，实现所有模型的api


20161020
-----------------
将dubbo改为motan,motan的配置代码来自github.com/chenxing2/spring-boot-starter-motan


20160914
---------------
1. 添加jwt支持


20160521
---------------
1. 添加dubbo，采用注解方式
2. dubbo的注解与使用示例，见[示例]: https://github.com/mazhaoyong/dubbox-zookeeper-demo/ "示例"


20160410
--------
1. 集成fastjson作为json格式化与解析器
2. 集成druid


20160407
--------
1. 升级mapper到最新版本3.3.8，重新适配，调整原有代码，由于扫描相关性，将mybatis相关代码统一放到了包orm下
2. 调整包名，按功能做一定区分
3. mybatis分页插件，采用mapper作者提供的插件
