package seed.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import seed.orm.dao.BaseDao;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;


@Configuration
public class MybatisCoinfig {

	
	@Bean
	public MapperScannerConfigurer getMapperConfig(){
		MapperScannerConfigurer config = new MapperScannerConfigurer();
		config.setBasePackage("seed");
		Properties props = new Properties();
		props.setProperty("mappers", BaseDao.class.getName());
		config.setProperties(props);
		return config;
	}
	
	
}
