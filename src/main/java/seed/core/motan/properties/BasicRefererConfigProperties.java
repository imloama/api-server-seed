package seed.core.motan.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * BasicReferer Config
 * 
 * @author 	alanwei
 * @since 	2016-09-13
 */
@Configuration
@ConfigurationProperties(prefix = "motan.basicreferer")
public class BasicRefererConfigProperties {
	
	/** 服务引用 BeanId */
	private String id;
	
	/** 使用的协议 default motan */
	private String protocol;
	
	/** 服务分组 defalut default_rpc */
	private String group;
	
	/** 模块信息 */
	private String module;
	
	/** 应用信息 */
	private String application;
	
	/** 注册中心的id 列表，多个用“,”分隔，如果为空，则使用所有的配置中心 */
	private String registry;
	
	/** 检查服务提供者是否存在 */
	private String check;
	
	/** 设为true，将向logger 中输出访问日志 */
	private Boolean accessLog;
	
	/** 重试次数 defalut 0 */
	private Integer retries;
	
	/** 抛出异常 default true */
	private Boolean throwException;

	/** 版本 default 1.0 */
	private String version;
	
	/** 是否共享channel default false */
	private Boolean shareChannel;
	
	/** 请求超时时间(毫秒) default 200 */
	private Integer requestTimeout;
	
	/** 过滤器配置 */
	private String filter;

	/** 扩展配置 */
	private String extConfigId;
	
	/** 最大请求数，0为不做并发限制 default 0 */
	private Integer actives;
	
	/** 方法是否异步 default false */
	private Boolean async;
	
	/** 协议编码 */
	private String codec;
	
	/** 是否开启gzip压缩.只有compressMotan的codec才能支持 */
	private Boolean usegz;
	
	/** 代理类型 */
	private String proxy;
	
	/** 
	 * 开启gzip压缩的阈值.usegz开关开启，且传输数据大于此阈值时，才会进行gzip压缩。
	 * 只有compressMotan的codec才能支持
	 */
	private Integer mingzSize;
	
	/** 
	 * 设为true，表示使用缺省Mock类名
	 * 即：接口名+Mock 后缀，服务接口调用失败Mock实现类
	 */
	private String mock;
	
	/** 以下4个文档未描述 */
	private String errorRate;
	private String localServiceAddress;
	private String mean;
	private Boolean defaultConfig;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the application
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * @return the registry
	 */
	public String getRegistry() {
		return registry;
	}

	/**
	 * @param registry the registry to set
	 */
	public void setRegistry(String registry) {
		this.registry = registry;
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
	 * @return the accessLog
	 */
	public Boolean getAccessLog() {
		return accessLog;
	}

	/**
	 * @param accessLog the accessLog to set
	 */
	public void setAccessLog(Boolean accessLog) {
		this.accessLog = accessLog;
	}

	/**
	 * @return the retries
	 */
	public Integer getRetries() {
		return retries;
	}

	/**
	 * @param retries the retries to set
	 */
	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	/**
	 * @return the throwException
	 */
	public Boolean getThrowException() {
		return throwException;
	}

	/**
	 * @param throwException the throwException to set
	 */
	public void setThrowException(Boolean throwException) {
		this.throwException = throwException;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the shareChannel
	 */
	public Boolean getShareChannel() {
		return shareChannel;
	}

	/**
	 * @param shareChannel the shareChannel to set
	 */
	public void setShareChannel(Boolean shareChannel) {
		this.shareChannel = shareChannel;
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
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * @return the extConfigId
	 */
	public String getExtConfigId() {
		return extConfigId;
	}

	/**
	 * @param extConfigId the extConfigId to set
	 */
	public void setExtConfigId(String extConfigId) {
		this.extConfigId = extConfigId;
	}

	/**
	 * @return the actives
	 */
	public Integer getActives() {
		return actives;
	}

	/**
	 * @param actives the actives to set
	 */
	public void setActives(Integer actives) {
		this.actives = actives;
	}

	/**
	 * @return the async
	 */
	public Boolean getAsync() {
		return async;
	}

	/**
	 * @param async the async to set
	 */
	public void setAsync(Boolean async) {
		this.async = async;
	}

	/**
	 * @return the codec
	 */
	public String getCodec() {
		return codec;
	}

	/**
	 * @param codec the codec to set
	 */
	public void setCodec(String codec) {
		this.codec = codec;
	}

	/**
	 * @return the usegz
	 */
	public Boolean getUsegz() {
		return usegz;
	}

	/**
	 * @param usegz the usegz to set
	 */
	public void setUsegz(Boolean usegz) {
		this.usegz = usegz;
	}

	/**
	 * @return the proxy
	 */
	public String getProxy() {
		return proxy;
	}

	/**
	 * @param proxy the proxy to set
	 */
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	/**
	 * @return the mingzSize
	 */
	public Integer getMingzSize() {
		return mingzSize;
	}

	/**
	 * @param mingzSize the mingzSize to set
	 */
	public void setMingzSize(Integer mingzSize) {
		this.mingzSize = mingzSize;
	}

	/**
	 * @return the mock
	 */
	public String getMock() {
		return mock;
	}

	/**
	 * @param mock the mock to set
	 */
	public void setMock(String mock) {
		this.mock = mock;
	}

	/**
	 * @return the errorRate
	 */
	public String getErrorRate() {
		return errorRate;
	}

	/**
	 * @param errorRate the errorRate to set
	 */
	public void setErrorRate(String errorRate) {
		this.errorRate = errorRate;
	}

	/**
	 * @return the localServiceAddress
	 */
	public String getLocalServiceAddress() {
		return localServiceAddress;
	}

	/**
	 * @param localServiceAddress the localServiceAddress to set
	 */
	public void setLocalServiceAddress(String localServiceAddress) {
		this.localServiceAddress = localServiceAddress;
	}

	/**
	 * @return the mean
	 */
	public String getMean() {
		return mean;
	}

	/**
	 * @param mean the mean to set
	 */
	public void setMean(String mean) {
		this.mean = mean;
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