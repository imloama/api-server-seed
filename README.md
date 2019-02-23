# api-server-seed
API服务基础脚手架搭建，采用spring-boot\spring-session\mybatis\redis\quartz\swagger2等，支持集群部署



开发部署
===
> 依赖环境
1. JDK 1.8
2. gradle
3. idea或eclipse，需要安装lombok插件，IDEA需要在"设置找到annotation processor，启用enable annotation processing"
4. mysql

> 部署步骤
1. 在mysql新建数据库api-seed，新建表
```sql
create table user(
     id int primary key auto_increment,#主键
     username varchar(56),#用户名
     password varchar(255)#密码
);
```
2. 修改application-dev.yml中的数据库连接配置信息，用户名与密码等
3. 运行APIApplication


新增内容开发
===
1. 新建表
2. 在model包中，新建对应的JavaBean，对应与该表，继承BaseModel，具体参考User类
3. 新建service，继承BaseServiceImpl，如果有接口，接口继承BaserService
4. 新建controller，继承BaseController
