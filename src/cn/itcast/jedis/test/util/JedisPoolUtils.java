package cn.itcast.jedis.test.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {

    private static JedisPool jedisPool;
    //静态方法读取配置文件
    static {
//        1.读取配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
//        2.创建Properties对象
        Properties pro = new Properties();
//        3.关联文件
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        设置JedisPoolConfig  获取数据
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        //初始化JedisPool
        jedisPool = new JedisPool(config,pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
    }

    /**
     *获取连接方法
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
