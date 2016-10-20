package seed.core.motan.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Registry Config
 * 
 * @author 	alanwei
 * @since 	2016-09-11
 */
@Configuration
@ConfigurationProperties(prefix = "motan.registry")
public class RegistryConfigProperties {

	/** 注册配置名称 */
	private String name;
	
	/** 注册协议 */
	private String regProtocol;
	
	/** 注册中心地址 */
	private String address;
	
	/** 注册中心端口 */
	private Integer port;
	
	/** 注册中心连接超时时间 (millisecond), default 1000 */
	private Integer connectTimeout;
	
	/** 注册中心请求超时时间 (millisecond), default 200 */
	private Integer requestTimeout;
	
	/** 注册中心会话超时时间 (millisecond), default 60 * 1000 */
	private Integer registrySessionTimeout;
	
	/** 失败后重试的时间间隔 (millisecond), default 30 * 1000 */
	private Integer registryRetryPeriod;
	
	/** 启动时检查失败后是否仍然启动, default true */
	private String check;
	
	/** 在该注册中心上服务是否暴露, default true */
	private Boolean register;
	
	/** 在该注册中心上服务是否引用, default true */
	private Boolean subscribe;
	
	/** 是否缺省的配置 */
	private Boolean defaultConfig;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the regProtocol
	 */
	public String getRegProtocol() {
		return regProtocol;
	}

	/**
	 * @param regProtocol the regProtocol to set
	 */
	public void setRegProtocol(String regProtocol) {
		this.regProtocol = regProtocol;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the connectTimeout
	 */
	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * @param connectTimeout the connectTimeout to set
	 */
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * @return the requestTimeout
	 */
	public Integer getRequestTimeout() {
		return requestTimeout;
	}

	/**
	 * @param requestTimeout the requestTimeout to set
	 */
	public void setRequestTimeout(Integer requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	/**
	 * @return the registrySessionTimeout
	 */
	public Integer getRegistrySessionTimeout() {
		return registrySessionTimeout;
	}

	/**
	 * @param registrySessionTimeout the registrySessionTimeout to set
	 */
	public void setRegistrySessionTimeout(Integer registrySessionTimeout) {
		this.registrySessionTimeout = registrySessionTimeout;
	}

	/**
	 * @return the registryRetryPeriod
	 */
	public Integer getRegistryRetryPeriod() {
		return registryRetryPeriod;
	}

	/**
	 * @param registryRetryPeriod the registryRetryPeriod to set
	 */
	public void setRegistryRetryPeriod(Integer registryRetryPeriod) {
		this.registryRetryPeriod = registryRetryPeriod;
	}

	/**
	 * @return the check
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * @return the register
	 */
	public Boolean getRegister() {
		return register;
	}

	/**
	 * @param register the register to set
	 */
	public void setRegister(Boolean register) {
		this.register = register;
	}

	/**
	 * @return the subscribe
	 */
	public Boolean getSubscribe() {
		return subscribe;
	}

	/**
	 * @param subscribe the subscribe to set
	 */
	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * @return the defaultConfig
	 */
	public Boolean getDefaultConfig() {
		return defaultConfig;
	}

	/**
	 * @param defaultConfig the defaultConfig to set
	 */
	public void setDefaultConfig(Boolean defaultConfig) {
		this.defaultConfig = defaultConfig;
	}
}