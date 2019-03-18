# api-server-seed
API服务基础脚手架搭建，采用spring-boot\spring-session\mybatis\redis\quartz\swagger2等，支持集群部署


依赖
===
1. springboot 2.1.3.RELEASE
2. spring-security
3. mybatis-plus
4. springfox-swagger2
5. mysql
6. undertow
7. lombok

开发部署
===
## 依赖环境
1. JDK 1.8
2. gradle或maven
3. idea或eclipse，需要安装lombok插件，IDEA需要在"设置找到annotation processor，启用enable annotation processing",具体参考lombok官方网站[https://projectlombok.org/](https://projectlombok.org/)
4. mysql

## 部署步骤
1. 在mysql新建数据库api-seed，新建表
```sql
create table user(
     id int primary key auto_increment,#主键
     username varchar(56),#用户名
     password varchar(255)#密码
);
INSERT INTO USER(username,password)values("admin","$2a$10$Q4OQlkJj043v4i97dhxzDO7AFpTOGWKHugNh9euqglYb5GN8MAoZO");
```
2. 启动redis
2. 修改application-dev.yml中的数据库连接配置信息，数据库的用户名与密码，redis的地址、用户名和密码等
3. 运行APIApplication


新增内容开发,具体参考demo包相关内容
===
1. 新建表
2. 在model包中，新建对应的JavaBean，对应与该表，继承BaseModel，具体参考User类
3. 新建service，继承BaseServiceImpl，如果有接口，接口继承BaserService
4. 新建controller，继承BaseController
5. 访问http://localhost:8080/swagger-ui.html，打开swagger-ui界面
6. 访问http://localhost:8080/api/v1/login，content-type=application/json,参数username=admin,password=admin，获取结果中，data即为token值
