package hands.on.testcontainers;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisBackedCache {

    private final JedisPool pool;

    public RedisBackedCache(String address, Integer port) {
        pool = new JedisPool(address, port);
    }

    public void put(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(key, value);
        }
    }

    public String get(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }
}
