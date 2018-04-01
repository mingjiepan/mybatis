package com.mybatis.base.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.Jedis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author panmingjie
 */
public class RedisCache implements Cache {

    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

    private String id;

    public RedisCache(final String id) {
        jedis.select(6);
        this.id = id;
    }

    @Override
    public String getId() {
        if(id == null) {
            throw new IllegalArgumentException("mybatis 缓存实例需要一个ID");
        }
        return this.id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        jedis.set(serializer.serialize(o), serializer.serialize(o1));
    }

    @Override
    public Object getObject(Object o) {
        byte[] bytes = jedis.get(serializer.serialize(o));
        Object deserialize = serializer.deserialize(bytes);
        return deserialize;
    }

    @Override
    public Object removeObject(Object o) {
        Long expire = jedis.expire(serializer.serialize(o), 0);
        return expire;
    }

    @Override
    public void clear() {
        jedis.flushDB();
    }

    @Override
    public int getSize() {
        int anInt = Integer.parseInt(jedis.dbSize().toString());
        return anInt;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
