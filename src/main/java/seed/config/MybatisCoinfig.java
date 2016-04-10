package seed.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import orm.mapper.BaseMapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
@AutoConfigureAfter(DruidConfig.class)
public class MybatisCoinfig {

	@Bean
	public MapperScannerConfigurer getMapperConfig() {
		MapperScannerConfigurer config = new MapperScannerConfigurer();
		config.setSqlSessionFactoryBeanName("sqlSessionFactory");
		config.setBasePackage("seed");
		Properties props = new Properties();
		props.setProperty("mappers", BaseMapper.class.getName());
		props.setProperty("notEmpty", "false");
		//props.setProperty("IDENTITY", "MYSQL");
		config.setProperties(props);
		return config;
	}
	
	@Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
