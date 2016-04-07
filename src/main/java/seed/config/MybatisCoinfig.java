package seed.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

import orm.mapper.BaseMapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
public class MybatisCoinfig {

	private static Logger log = LoggerFactory.getLogger(MybatisCoinfig.class);

	@Bean
	public PageHelper pageHelper(DataSource dataSource) {
		log.info("注册MyBatis分页插件PageHelper");
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}

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

}
