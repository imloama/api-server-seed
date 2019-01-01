package com.github.imloama.api.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// mybatis的mapper文件保存位置
@MapperScan("com.github.imloama.api.*.mapper")
public class MybatisConfig {
}
