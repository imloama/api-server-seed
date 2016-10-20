package seed.core.motan.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * BasicServic Config
 * 
 * @author 	alanwei
 * @since 	2016-09-11
 */
@Configuration
@ConfigurationProperties(prefix = "motan.basicservice")
public class BasicServiceConfigProperties {

	private String id;
	
	/** 服务暴露的方式，包含协议及端口号，多个协议端口用"," 分隔 */
	private String export;
	
	/** 服务暴露端口 */
	private String exportPort;
	
	/** 扩展配置Id */
	private String extConfigId;
	
	/** 代理类型 */
	private String proxy;
	
	/** 服务分组, default default_rpc */
	private String group;
	
	/** 版本, default 1.0 */
	private String version;
	
	/** 抛出异常, default true */
	private Boolean throwException;
	
	/** 应用信息, default motan */
	private String application;
	
	/** 是否共享channel, default false */
	private Boolean shareChannel;
	
	/** 注册中心的id 列表，多个用“,”分隔，如果为空，则使用所有的配置中心 */
	private String registry;
	
	/** 设为true，将向logger 中输出访问日志, default false */
	private Boolean accessLog;
	
	/** 是否开启gzip压缩.只有compressMotan的codec才能支持, default false */
	private Boolean usegz;
	
	/** 
	 * 开启gzip压缩的阈值.usegz开关开启，且传输数据大于此阈值时，才会进行gzip压缩。
	 * 只有compressMotan的codec才能支持.
	 * 
	 * default 1000
	 */
	private Integer mingzSize;
	
	/** 协议编码, default motan */
	private String codec;
	
	/** 过滤器配置 */
	private String filter;
	
	/** 模块信息, default motan */
	private String module;
	
	/** 最大请求数，0为不做并发限制, default 0 */
	private Integer actives;
	
	/** 在该注册中心上服务是否暴露, default true */	
	private Boolean register;

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
	 * @return the export
	 */
	public String getExport() {
		return export;
	}

	/**
	 * @param export the export to set
	 */
	public void setExport(String export) {
		this.export = export;
	}

	/**
	 * @return the exportPort
	 */
	public String getExportPort() {
		return exportPort;
	}

	/**
	 * @param exportPort the exportPort to set
	 */
	public void setExportPort(String exportPort) {
		this.exportPort = exportPort;
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
}