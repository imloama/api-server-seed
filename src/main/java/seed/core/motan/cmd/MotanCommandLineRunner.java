package seed.core.motan.cmd;


import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

import seed.core.motan.properties.RegistryConfigProperties;

/**
 * MotanCommandLineRunner
 * 
 * @author 	alanwei
 * @since 	2016-09-11
 */
@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class MotanCommandLineRunner implements CommandLineRunner {

	/** Registry Config */
	@Resource
	private RegistryConfigProperties registryConfig;
	
	@Override
	public void run(String... args) throws Exception {
		
		if (!registryConfig.getRegProtocol().toLowerCase().equals("local")) {
			// 开启注册中心
			MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		}
	}

}
