package com.sxs.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedis {

    @Test
    public void a(){
        // 指定redis服务器地址和端口号
        Jedis jedis = new Jedis("192.168.153.128", 6379);
        jedis.set("tom:code","123456");
        System.out.println(jedis.get("tom:code"));
        // 关闭连接
        jedis.close();
    }

    @Test
    public void b(){
        // 创建连接池
        JedisPool jedisPool = new JedisPool("192.168.153.128", 6379);
        // 我们可以对连接池进行设置
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        // 最大连接数
        genericObjectPoolConfig.setMaxTotal(20);
        // 最大闲置连接数
        genericObjectPoolConfig.setMaxIdle(20);
        // 最小闲置连接数
        genericObjectPoolConfig.setMinIdle(0);
        // 最长连接时间
        genericObjectPoolConfig.setMaxWaitMillis(3000);
        // 使用连接池获取连接
        Jedis resource = jedisPool.getResource();
        System.out.println(resource.get("tomname"));
        resource.close();
    }
}
