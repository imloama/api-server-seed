package seed.core.motan.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Protocol Config
 * 
 * @author 	alanwei
 * @since 	2016-09-11
 */
@Configuration
@ConfigurationProperties(prefix = "motan.protocol")
public class ProtocolConfigProperties {

	/** 服务协议名 */
	private String name;
	
	/** 序列化方式, default hessian2 */
	private String serialization;
	
	/** 最大请求数据长度 */
	private Integer payload;
	
	/** 缓存区大小 */
	private Integer buffer;
	
	/** 心跳间隔 */
	private Integer heartbeat;
	
	/** 网络传输方式 */
	private String transporter;
	
	/** 线程池大小 */
	private Integer threads;
	
	/** IO线程池大小, default availableProcessors + 1 */
	private Integer iothreads;
	
	/** 请求超时, default 200 */
	private Integer requestTimeout;
	
	/** client最小连接数, default 2 */
	private Integer minClientConnection;
	
	/** client最大连接数, default 10 */
	private Integer maxClientConnection;
	
	/** 最小工作pool线程数, default 20 */
	private Integer minWorkerThread;
	
	/** 最大工作pool线程数, default 200 */
	private Integer maxWorkerThread;
	
	/** 请求响应包的最大长度限制, default 10M */
	private Integer maxContentLength;
	
	/** server支持的最大连接数, default 100000 */
	private Integer maxServerConnection;
	
	/** 连接池管理方式，是否lifo, default true */
	private Boolean poolLifo;

	/** 是否延迟init, default false */
	private Boolean lazyInit;
	
	/** endpoint factory, defalut motan */
	private String endpointFactory;
	
	/** 采用哪种cluster的实现, defalut defalut */
	private String cluster;

	/** 负载均衡策略, defalut activeWeight */
	private String loadbalance;
	
	/** 高可用策略, defalut failover */
	private String haStrategy;
	
	/** Server工作队列大小, defalut 0 */
	private Integer workerQueueSize;
	
	/** Server可接受连接数, defalut 0 */
	private Integer acceptConnections;
	
	/** proxy type, like jdk or javassist, default jdk */
	private String proxy;
	
	/** filter, 多个filter用","分割，blank String 表示采用默认的filter配置 */
	private String filter;
	
	/** 调用失败时重试次数, defalut 0 */
	private Integer retries;
	
	/** if the request is called async, a taskFuture result will be sent back, defalut false */
	private Boolean async;
	
	/** 线程池队列大小 */
	private Integer queueSize;
	
	/** 最大接收连接数 */
	private Integer accepts;
	
	/** 信息线程模型派发方式 */
	private String dispatcher;
	
	/** 服务器端实现 */
	private String server;
	
	/** 客户端端实现 */
	private String client;
	
	/** 是否缺省的配置 */
	private Boolean defaultConfig;
	
	/** default localSwitcherService */
	private String switcherService;
	
	/** default motan */
	private String heartbeatFactory;

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
	 * @return the serialization
	 */
	public String getSerialization() {
		return serialization;
	}

	/**
	 * @param serialization the serialization to set
	 */
	public void setSerialization(String serialization) {
		this.serialization = serialization;
	}

	/**
	 * @return the payload
	 */
	public Integer getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(Integer payload) {
		this.payload = payload;
	}

	/**
	 * @return the buffer
	 */
	public Integer getBuffer() {
		return buffer;
	}

	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(Integer buffer) {
		this.buffer = buffer;
	}

	/**
	 * @return the heartbeat
	 */
	public Integer getHeartbeat() {
		return heartbeat;
	}

	/**
	 * @param heartbeat the heartbeat to set
	 */
	public void setHeartbeat(Integer heartbeat) {
		this.heartbeat = heartbeat;
	}

	/**
	 * @return the transporter
	 */
	public String getTransporter() {
		return transporter;
	}

	/**
	 * @param transporter the transporter to set
	 */
	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}

	/**
	 * @return the threads
	 */
	public Integer getThreads() {
		return threads;
	}

	/**
	 * @param threads the threads to set
	 */
	public void setThreads(Integer threads) {
		this.threads = threads;
	}

	/**
	 * @return the iothreads
	 */
	public Integer getIothreads() {
		return iothreads;
	}

	/**
	 * @param iothreads the iothreads to set
	 */
	public void setIothreads(Integer iothreads) {
		this.iothreads = iothreads;
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
	 * @return the minClientConnection
	 */
	public Integer getMinClientConnection() {
		return minClientConnection;
	}

	/**
	 * @param minClientConnection the minClientConnection to set
	 */
	public void setMinClientConnection(Integer minClientConnection) {
		this.minClientConnection = minClientConnection;
	}

	/**
	 * @return the maxClientConnection
	 */
	public Integer getMaxClientConnection() {
		return maxClientConnection;
	}

	/**
	 * @param maxClientConnection the maxClientConnection to set
	 */
	public void setMaxClientConnection(Integer maxClientConnection) {
		this.maxClientConnection = maxClientConnection;
	}

	/**
	 * @return the minWorkerThread
	 */
	public Integer getMinWorkerThread() {
		return minWorkerThread;
	}

	/**
	 * @param minWorkerThread the minWorkerThread to set
	 */
	public void setMinWorkerThread(Integer minWorkerThread) {
		this.minWorkerThread = minWorkerThread;
	}

	/**
	 * @return the maxWorkerThread
	 */
	public Integer getMaxWorkerThread() {
		return maxWorkerThread;
	}

	/**
	 * @param maxWorkerThread the maxWorkerThread to set
	 */
	public void setMaxWorkerThread(Integer maxWorkerThread) {
		this.maxWorkerThread = maxWorkerThread;
	}

	/**
	 * @return the maxContentLength
	 */
	public Integer getMaxContentLength() {
		return maxContentLength;
	}

	/**
	 * @param maxContentLength the maxContentLength to set
	 */
	public void setMaxContentLength(Integer maxContentLength) {
		this.maxContentLength = maxContentLength;
	}

	/**
	 * @return the maxServerConnection
	 */
	public Integer getMaxServerConnection() {
		return maxServerConnection;
	}

	/**
	 * @param maxServerConnection the maxServerConnection to set
	 */
	public void setMaxServerConnection(Integer maxServerConnection) {
		this.maxServerConnection = maxServerConnection;
	}

	/**
	 * @return the poolLifo
	 */
	public Boolean getPoolLifo() {
		return poolLifo;
	}

	/**
	 * @param poolLifo the poolLifo to set
	 */
	public void setPoolLifo(Boolean poolLifo) {
		this.poolLifo = poolLifo;
	}

	/**
	 * @return the lazyInit
	 */
	public Boolean getLazyInit() {
		return lazyInit;
	}

	/**
	 * @param lazyInit the lazyInit to set
	 */
	public void setLazyInit(Boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	/**
	 * @return the endpointFactory
	 */
	public String getEndpointFactory() {
		return endpointFactory;
	}

	/**
	 * @param endpointFactory the endpointFactory to set
	 */
	public void setEndpointFactory(String endpointFactory) {
		this.endpointFactory = endpointFactory;
	}

	/**
	 * @return the cluster
	 */
	public String getCluster() {
		return cluster;
	}

	/**
	 * @param cluster the cluster to set
	 */
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	/**
	 * @return the loadbalance
	 */
	public String getLoadbalance() {
		return loadbalance;
	}

	/**
	 * @param loadbalance the loadbalance to set
	 */
	public void setLoadbalance(String loadbalance) {
		this.loadbalance = loadbalance;
	}

	/**
	 * @return the haStrategy
	 */
	public String getHaStrategy() {
		return haStrategy;
	}

	/**
	 * @param haStrategy the haStrategy to set
	 */
	public void setHaStrategy(String haStrategy) {
		this.haStrategy = haStrategy;
	}

	/**
	 * @return the workerQueueSize
	 */
	public Integer getWorkerQueueSize() {
		return workerQueueSize;
	}

	/**
	 * @param workerQueueSize the workerQueueSize to set
	 */
	public void setWorkerQueueSize(Integer workerQueueSize) {
		this.workerQueueSize = workerQueueSize;
	}

	/**
	 * @return the acceptConnections
	 */
	public Integer getAcceptConnections() {
		return acceptConnections;
	}

	/**
	 * @param acceptConnections the acceptConnections to set
	 */
	public void setAcceptConnections(Integer acceptConnections) {
		this.acceptConnections = acceptConnections;
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
	 * @return the queueSize
	 */
	public Integer getQueueSize() {
		return queueSize;
	}

	/**
	 * @param queueSize the queueSize to set
	 */
	public void setQueueSize(Integer queueSize) {
		this.queueSize = queueSize;
	}

	/**
	 * @return the accepts
	 */
	public Integer getAccepts() {
		return accepts;
	}

	/**
	 * @param accepts the accepts to set
	 */
	public void setAccepts(Integer accepts) {
		this.accepts = accepts;
	}

	/**
	 * @return the dispatcher
	 */
	public String getDispatcher() {
		return dispatcher;
	}

	/**
	 * @param dispatcher the dispatcher to set
	 */
	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
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

	/**
	 * @return the switcherService
	 */
	public String getSwitcherService() {
		return switcherService;
	}

	/**
	 * @param switcherService the switcherService to set
	 */
	public void setSwitcherService(String switcherService) {
		this.switcherService = switcherService;
	}

	/**
	 * @return the heartbeatFactory
	 */
	public String getHeartbeatFactory() {
		return heartbeatFactory;
	}

	/**
	 * @param heartbeatFactory the heartbeatFactory to set
	 */
	public void setHeartbeatFactory(String heartbeatFactory) {
		this.heartbeatFactory = heartbeatFactory;
	}
}