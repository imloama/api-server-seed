package seed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

@Configuration
@ConfigurationProperties(prefix = DubboConfig.DUBBO_PREFIX)
public class DubboConfig {

	
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(getApplicationName());
        applicationConfig.setOwner(getApplicationOwner());
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(getRegistryAddress());
        return registryConfig;
    }
    
    @Bean
    public ProtocolConfig protocalConfig(){
    	ProtocolConfig protocolConfig = new ProtocolConfig();
    	protocolConfig.setName(getProtocolName());
    	protocolConfig.setPort(getProtocalPort());
    	return protocolConfig;
    }

    @Bean
    public AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(getBasePackage());
        return annotationBean;
    }
    
    public static final String DUBBO_PREFIX = "dubbo";

	private String applicationName;

	private String applicationOwner;

	private String basePackage;

	private String protocolName;

	private Integer protocalPort;

	private String registryAddress;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationOwner() {
		return applicationOwner;
	}

	public void setApplicationOwner(String applicationOwner) {
		this.applicationOwner = applicationOwner;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public Integer getProtocalPort() {
		return protocalPort;
	}

	public void setProtocalPort(Integer protocalPort) {
		this.protocalPort = protocalPort;
	}

}