package com.hou.springboot.api.utils;

import redis.clients.jedis.Jedis;

public class RedisUtil {
    //赋值
    public static void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getInstall();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(jedis!=null){
                jedis.close();

            }
        }
    }

    //获取值
    public static String get(String key){
        Jedis jedis = null;
        String value = null;
        try {
            jedis= RedisPool.getInstall();
            value= jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(jedis!=null){
                jedis.close();

            }
        }
        return value;
    }
    //删除
    public static Long delete(String key){
        Jedis jedis = null;
        Long delToken = null;
        try {
            jedis = RedisPool.getInstall();
             delToken = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(jedis!=null){
                jedis.close();

            }
        }
        return delToken;
    }

    //设置定时消亡时间
    public static void setEx(String key, int seconds,String value){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getInstall();
            jedis.setex(key,seconds,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(jedis!=null){
                jedis.close();

            }
        }
    }

        //判断 是否存在
    public static boolean Exist(String key){
        Jedis jedis = null;
        boolean exist = false;
        try {
            jedis = RedisPool.getInstall();
            exist=jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(jedis!=null){
                jedis.close();

            }
        }
        return  exist;
    }
    //存入哈希中
    public static void Hset(String key,String field,String value){
        Jedis jedis = null;

        try {
            jedis = RedisPool.getInstall();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(jedis!=null){
                jedis.close();

            }
        }

    }

    //哈希获取值
    public static String Hget(String key,String field){
        Jedis jedis = null;
        String value = null;
        try {
            jedis= RedisPool.getInstall();
            value= jedis.hget(key,field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(jedis!=null){
                jedis.close();

            }
        }
        return value;
    }
    //哈希删除
    public static void hdel(String key,String field){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getInstall();
            jedis.hdel(key,field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(jedis!=null){
                jedis.close();

            }
        }
    }

}
