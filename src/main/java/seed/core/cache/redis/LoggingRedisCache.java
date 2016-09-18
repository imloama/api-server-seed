package seed.core.cache.redis;

import org.apache.ibatis.cache.decorators.LoggingCache;

public class LoggingRedisCache extends LoggingCache {

	public LoggingRedisCache(String id) {
		super(new RedisCache(id));
	}

	
}
