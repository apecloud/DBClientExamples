import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SentinelRedisExample {
	private static final Logger logger = LoggerFactory.getLogger(SentinelRedisExample.class);
	public static JedisSentinelPool createJedisSentinelPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();

		Set<String> sentinelSet = new HashSet<>();
		sentinelSet.add(new HostAndPort("172.31.21.153", 30281).toString());
		sentinelSet.add(new HostAndPort("172.31.21.68", 31339).toString());
		sentinelSet.add(new HostAndPort("172.31.19.121", 32720).toString());

		// master-name, sentinel-hosts, redis-password, sentinel-password
		return new JedisSentinelPool("maple-b5f64d784-redis", sentinelSet, "U2s376oS0S", "w10S8N2oL7");
	}

	public static void main(String[] args) {
		JedisSentinelPool jedisSentinelPool = createJedisSentinelPool();

		try (Jedis jedis = jedisSentinelPool.getResource()) {
			jedis.set("corp", "ApeCloud");
			String value = jedis.get("corp");
			System.out.println("corp: " + value);
		}
	}
}

