package seed.core.motan;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.util.StringUtils;

import com.weibo.api.motan.config.ExtConfig;
import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;

import seed.core.motan.properties.BasicRefererConfigProperties;
import seed.core.motan.properties.BasicServiceConfigProperties;
import seed.core.motan.properties.ProtocolConfigProperties;
import seed.core.motan.properties.RegistryConfigProperties;
import seed.core.motan.support.BasicServiceConfigCondition;

/**
 * Motan Config
 * 
 * @author 	alanwei
 * @since 	2016-09-11
 */
@ComponentScan
public class MotanConfig {
	
	/** 注册中心配置bean名称 */
	private static final String REGISTRY_CONFIG_BEAN_NAME = "_spring-boot-starter-motan-registry_";
	/** 协议配置bean名称 */
	private static final String PROTOCOL_CONFIG_BEAN_NAME = "_spring-boot-starter-motan-protocol_";
	
	/**
	 * define AnnotationBean
	 */
	@Bean
	public AnnotationBean annotationBean(@Value("${motan.annotation.package}") String scanPackage) {
		AnnotationBean annotationBean = new AnnotationBean();
		if (!StringUtils.isEmpty(scanPackage)) {
			annotationBean.setPackage(scanPackage);
		}
		return annotationBean;
	}
	
	/**
	 * define RegistryConfigBean
	 */
	@Bean(name = REGISTRY_CONFIG_BEAN_NAME)
    public RegistryConfigBean registryConfig(RegistryConfigProperties registryConfig) {
        RegistryConfigBean config = new RegistryConfigBean();
        config.setName(REGISTRY_CONFIG_BEAN_NAME);
        if (!StringUtils.isEmpty(registryConfig.getRegProtocol())) {
        	config.setRegProtocol(registryConfig.getRegProtocol());
        }
        if (!StringUtils.isEmpty(registryConfig.getAddress())) {
        	config.setAddress(registryConfig.getAddress());
        }
		if (registryConfig.getPort() != null) {
			config.setPort(registryConfig.getPort());
		}
		if (registryConfig.getConnectTimeout() != null) {
			config.setConnectTimeout(registryConfig.getConnectTimeout());
		}
		if (registryConfig.getRequestTimeout() != null) {
			config.setRequestTimeout(registryConfig.getRequestTimeout());
		}
		if (registryConfig.getRegistrySessionTimeout() != null) {
			config.setRegistrySessionTimeout(registryConfig.getRegistrySessionTimeout());
		}
		if (registryConfig.getRegistryRetryPeriod() != null) {
			config.setRegistryRetryPeriod(registryConfig.getRegistryRetryPeriod());
		}
		if (!StringUtils.isEmpty(registryConfig.getCheck())) {
			config.setCheck(registryConfig.getCheck());
		}
		if (registryConfig.getRegister() != null) {
			config.setRegister(registryConfig.getRegister());
		}
		if (registryConfig.getSubscribe() != null) {
			config.setSubscribe(registryConfig.getSubscribe());
		}
		if (registryConfig.getDefaultConfig() != null) {
			config.setDefault(registryConfig.getDefaultConfig());
		}
        return config;
    }
	
	/**
	 * define ProtocolConfigBean
	 * 
	 * 属性来自Motan的配置文档
	 * @see https://github.com/weibocom/motan/blob/master/docs/wiki/zh_configuration.md
	 * 
	 * 注释的代码，在Motan0.21版本中已不支持这些属性设置，可使用表示相同意义的参数设置
	 */
	@Bean(name = PROTOCOL_CONFIG_BEAN_NAME)
    public ProtocolConfigBean protocolConfig(ProtocolConfigProperties protocolConfig) {
        ProtocolConfigBean config = new ProtocolConfigBean();
        // 如果未配置，则默认设置为motan
        if (!StringUtils.isEmpty(protocolConfig.getName())) {
        	config.setName(protocolConfig.getName());
        } else {
        	config.setName("motan");
        }
        if (!StringUtils.isEmpty(protocolConfig.getSerialization())) {
        	config.setSerialization(protocolConfig.getSerialization());
        }
//        config.setpayload
//        config.setuffer
//        config.sethearbeat
//        config.settransporter
//        config.setthreads
        if (protocolConfig.getIothreads() != null) {
        	config.setIothreads(protocolConfig.getIothreads());
        }
        if (protocolConfig.getRequestTimeout() != null) {
        	config.setRequestTimeout(protocolConfig.getRequestTimeout());
        }
        if (protocolConfig.getMinClientConnection() != null) {
        	config.setMinClientConnection(protocolConfig.getMinClientConnection());
        }
        if (protocolConfig.getMaxClientConnection() != null) {
        	config.setMaxClientConnection(protocolConfig.getMaxClientConnection());
        }
        if (protocolConfig.getMinWorkerThread() != null) {
        	config.setMinWorkerThread(protocolConfig.getMinWorkerThread());
        }
        if (protocolConfig.getMaxClientConnection() != null) {
        	config.setMaxClientConnection(protocolConfig.getMaxClientConnection());
        }
        if (protocolConfig.getMaxContentLength() != null) {
        	config.setMaxContentLength(protocolConfig.getMaxContentLength());
        }
        if (protocolConfig.getMaxServerConnection() != null) {
        	config.setMaxServerConnection(protocolConfig.getMaxServerConnection());
        }
        if (protocolConfig.getPoolLifo() != null) {
        	config.setPoolLifo(protocolConfig.getPoolLifo());
        }
        if (protocolConfig.getLazyInit() != null) {
        	config.setLazyInit(protocolConfig.getLazyInit());
        }
        if (!StringUtils.isEmpty(protocolConfig.getEndpointFactory())) {
        	config.setEndpointFactory(protocolConfig.getEndpointFactory());
        }
        if (!StringUtils.isEmpty(protocolConfig.getCluster())) {
        	config.setCluster(protocolConfig.getCluster());
        }
        if (!StringUtils.isEmpty(protocolConfig.getLoadbalance())) {
        	config.setLoadbalance(protocolConfig.getLoadbalance());
        }
        if (!StringUtils.isEmpty(protocolConfig.getHaStrategy())) {
        	config.setHaStrategy(protocolConfig.getHaStrategy());
        }
        if (protocolConfig.getWorkerQueueSize() != null) {
        	config.setWorkerQueueSize(protocolConfig.getWorkerQueueSize());
        }
        if (protocolConfig.getAcceptConnections() != null) {
        	config.setAcceptConnections(protocolConfig.getAcceptConnections());
        }
        if (!StringUtils.isEmpty(protocolConfig.getProxy())) {
        	config.setProxy(protocolConfig.getProxy());
        }
        if (!StringUtils.isEmpty(protocolConfig.getFilter())) {
        	config.setFilter(protocolConfig.getFilter());
        }
        if (protocolConfig.getRetries() != null) {
        	config.setRetries(protocolConfig.getRetries());
        }
        if (protocolConfig.getAsync() != null) {
        	config.setAsync(protocolConfig.getAsync());
        }
//        config.setqueuesize
//        config.setaccepts
//        config.setdispatcher
//        config.setserver
//        config.setclient
        if (protocolConfig.getDefaultConfig() != null) {
        	config.setDefault(protocolConfig.getDefaultConfig());
        }
//        config.setswitcherservice
//        config.sethearBeatFactory
        
        return config;
    }
	
	/**
	 * define BasicServiceConfigBean
	 * 
	 * 属性来自Motan的配置文档
	 * @see https://github.com/weibocom/motan/blob/master/docs/wiki/zh_configuration.md
	 * 
	 * 挑了一些属性，不全，后续补全
	 */
	@Bean
	@Conditional(BasicServiceConfigCondition.class)
    public BasicServiceConfigBean baseServiceConfig(BasicServiceConfigProperties basicServiceConfig, RegistryConfigBean registryConfigBean) {
        BasicServiceConfigBean config = new BasicServiceConfigBean();
        
        if (!StringUtils.isEmpty(basicServiceConfig.getExport())) {
        	config.setExport(basicServiceConfig.getExport());
        } else {
        	// 未设置export，使用ProtocolConfigBeanName : port暴露
        	if (StringUtils.isEmpty(basicServiceConfig.getExportPort())) {
            	throw new RuntimeException("need service export port...");
            }
        	config.setExport(PROTOCOL_CONFIG_BEAN_NAME + ":" + basicServiceConfig.getExportPort());
        }
        
        if (!StringUtils.isEmpty(basicServiceConfig.getExtConfigId())) {
	        ExtConfig extConfig = new ExtConfig();
	        extConfig.setId(basicServiceConfig.getExtConfigId());
	        config.setExtConfig(extConfig);
        }
		if (!StringUtils.isEmpty(basicServiceConfig.getProxy())) {
			config.setProxy(basicServiceConfig.getProxy());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getGroup())) {
			config.setGroup(basicServiceConfig.getGroup());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getVersion())) {
			config.setVersion(basicServiceConfig.getVersion());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getThrowException())) {
			config.setThrowException(basicServiceConfig.getThrowException());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getApplication())) {
			config.setApplication(basicServiceConfig.getApplication());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getShareChannel())) {
			config.setShareChannel(basicServiceConfig.getShareChannel());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getRegistry())) {
			// 追加内部的注册配置bean
			config.setRegistry(REGISTRY_CONFIG_BEAN_NAME + "," + basicServiceConfig.getRegistry());
		} else {
			config.setRegistry(REGISTRY_CONFIG_BEAN_NAME);
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getAccessLog())) {
			config.setAccessLog(basicServiceConfig.getAccessLog());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getUsegz())) {
			config.setUsegz(basicServiceConfig.getUsegz());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getMingzSize())) {
			config.setMingzSize(basicServiceConfig.getMingzSize());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getCodec())) {
			config.setCodec(basicServiceConfig.getCodec());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getFilter())) {
			config.setFilter(basicServiceConfig.getFilter());
		}
		if (!StringUtils.isEmpty(basicServiceConfig.getModule())) {
			config.setModule(basicServiceConfig.getModule());
		}
		if (basicServiceConfig.getActives() != null) {
			config.setActives(basicServiceConfig.getActives());
		}
		if (basicServiceConfig.getRegister() != null) {
			config.setRegister(basicServiceConfig.getRegister());
		}
		
        return config;
    }
	
	/**
	 * define BasicRefererConfigBean
	 * 
	 * 属性来自Motan的配置文档
	 * @see https://github.com/weibocom/motan/blob/master/docs/wiki/zh_configuration.md
	 * 
	 * 挑了一些属性，不全，后续补全
	 */
	@Bean
    public BasicRefererConfigBean baseRefererConfig(BasicRefererConfigProperties basicRefererConfig) {
        BasicRefererConfigBean config = new BasicRefererConfigBean();
        
        config.setProtocol(PROTOCOL_CONFIG_BEAN_NAME);
        if (!StringUtils.isEmpty(basicRefererConfig.getGroup())) {
        	config.setGroup(basicRefererConfig.getGroup());
        }
        if (!StringUtils.isEmpty(basicRefererConfig.getModule())) {
        	config.setModule(basicRefererConfig.getModule());
        }
        if (!StringUtils.isEmpty(basicRefererConfig.getApplication())) {
        	config.setApplication(basicRefererConfig.getApplication());
        }
        if (!StringUtils.isEmpty(basicRefererConfig.getRegistry())) {
			// 追加内部的注册配置bean
			config.setRegistry(REGISTRY_CONFIG_BEAN_NAME + "," + basicRefererConfig.getRegistry());
		} else {
			config.setRegistry(REGISTRY_CONFIG_BEAN_NAME);
		}
        if (!StringUtils.isEmpty(basicRefererConfig.getCheck())) {
        	config.setCheck(basicRefererConfig.getCheck());
        }
        if (basicRefererConfig.getAccessLog() != null) {
			config.setAccessLog(basicRefererConfig.getAccessLog());
		}
        if (basicRefererConfig.getRetries() != null) {
        	config.setRetries(basicRefererConfig.getRetries());
        }
        if (basicRefererConfig.getThrowException() != null) {
        	config.setThrowException(basicRefererConfig.getThrowException());
        }
        if (!StringUtils.isEmpty(basicRefererConfig.getId())) {
        	config.setId(basicRefererConfig.getId());
        }
        if (!StringUtils.isEmpty(basicRefererConfig.getVersion())) {
        	config.setVersion(basicRefererConfig.getVersion());
        }
        if (basicRefererConfig.getShareChannel() != null) {
			config.setShareChannel(basicRefererConfig.getShareChannel());
		}
        if (basicRefererConfig.getRequestTimeout() != null) {
			config.setRequestTimeout(basicRefererConfig.getRequestTimeout());
		}
        if (!StringUtils.isEmpty(basicRefererConfig.getFilter())) {
        	config.setFilter(basicRefererConfig.getFilter());
        }
        if (!StringUtils.isEmpty(basicRefererConfig.getExtConfigId())) {
	        ExtConfig extConfig = new ExtConfig();
	        extConfig.setId(basicRefererConfig.getExtConfigId());
	        config.setExtConfig(extConfig);
        }
        if (basicRefererConfig.getActives() != null) {
			config.setActives(basicRefererConfig.getActives());
		}
        if (basicRefererConfig.getAsync() != null) {
			config.setAsync(basicRefererConfig.getAsync());
		}
        if (!StringUtils.isEmpty(basicRefererConfig.getCodec())) {
        	config.setCodec(basicRefererConfig.getCodec());
        }
        if (basicRefererConfig.getUsegz() != null) {
			config.setUsegz(basicRefererConfig.getUsegz());
		}
        if (basicRefererConfig.getMingzSize() != null) {
        	config.setMingzSize(basicRefererConfig.getMingzSize());
        }
        if (!StringUtils.isEmpty(basicRefererConfig.getProxy())) {
        	config.setProxy(basicRefererConfig.getProxy());
        }
        if (!StringUtils.isEmpty(basicRefererConfig.getMock())) {
        	config.setMock(basicRefererConfig.getMock());
        }
        
        // 文档未描述的4个属性暂不处理
        
        
        return config;
    }
}