package com.hou.springboot.api.brand.hou.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class  RedisPool  {

    //私有的有参
    private RedisPool(){

    }
    //静态
    private static JedisPool jedisPool;

    private static  void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(900);
        jedisPoolConfig.setMinIdle(50);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig,"192.168.28.132",7788);

       /*//集群模式
        Set<HostAndPort> nodes = new HashSet<>();

        HostAndPort hostAndPort1 = new HostAndPort("192.168.28.129", 7000);
        HostAndPort hostAndPort2 = new HostAndPort("192.168.28.129", 7001);
        HostAndPort hostAndPort3 = new HostAndPort("192.168.28.129", 7002);
        HostAndPort hostAndPort4= new HostAndPort("192.168.28.128", 7003);
        HostAndPort hostAndPort5 = new HostAndPort("192.168.28.128", 7004);
        HostAndPort hostAndPort6 = new HostAndPort("192.168.28.128", 7005);
j       edisCluster = new JedisCluster(nodes, jedisPoolConfig);

        nodes.add(hostAndPort1);
        nodes.add(hostAndPort2);
        nodes.add(hostAndPort3);
        *//*nodes.add(hostAndPort4);
        nodes.add(hostAndPort5);
        nodes.add(hostAndPort6);*/



    }

    //静态块
    static{
        initPool();
    }

    public static Jedis getInstall(){
        return jedisPool.getResource();

    }
}
