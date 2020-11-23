package cn.itcast.jedis.test;

import cn.itcast.jedis.test.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis测试类
 */
public class JedisTest {

    @Test
    public void test1() {
//        1.获取连接
        Jedis jedis = new Jedis("localhost",6379);
//        2.操作
        jedis.set("username","zhangsan");
//        3.关闭连接
        jedis.close();
    }

    /**
     * String数据结构
     */
    @Test
    public void test2() {
//        1.获取连接
        Jedis jedis = new Jedis(); //如果使用空参构造，默认值 "localhost",6379端口
//        2.操作
        jedis.set("username","zhangsan");//存储
        String username = jedis.get("username");
        System.out.println(username);

//        使用 setex()方法存储 可以指定过期时间
        jedis.setex("activecode",20,"激活码");//将activecode "激活码" 键值对存入redis,并且20秒后自动删除
//        3.关闭连接
        jedis.close();
    }

    /**
     * Hash数据结构
     */
    @Test
    public void test3() {
//        1.获取连接
        Jedis jedis = new Jedis(); //如果使用空参构造，默认值 "localhost",6379端口
//        2.操作
//        存储hash
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","男");

//        获取hash
        String name = jedis.hget("user", "name");
        System.out.println(name);

//        删除  user集合中的age
        jedis.hdel("user","age");

//        获取hash中所有的map数据
        Map<String, String> user = jedis.hgetAll("user");
        //keyset 循环打印输出 键值对
        Set<String> keyset = user.keySet();
        for(String key : keyset){
            String value = user.get(key);
            System.out.println(key+":"+value);
        }
//        3.关闭连接
        jedis.close();
    }

    /**
     * List数据结构
     */
    @Test
    public void test4() {
//        1.获取连接
        Jedis jedis = new Jedis(); //如果使用空参构造，默认值 "localhost",6379端口
//        2.操作
//        list存储
        jedis.lpush("mylist","a","b","c");//从左边存
        jedis.rpush("mylist","a","b","c");//从右边存
//        list获取
        List<String> list = jedis.lrange("mylist", 0, -1);
        System.out.println(list);
//        list弹出
        String e1 = jedis.lpop("mylist");//获取集和最左边的元素
        String e2 = jedis.rpop("mylist");//获取集合最右边的元素
        System.out.println(e1);
        System.out.println(e2);

//        list获取所有
        List<String> list1 = jedis.lrange("mylist", 0, -1);
        System.out.println(list1);
//        3.关闭连接
        jedis.close();
    }
//    Set数据结构
    @Test
    public void test5() {
//        1.获取连接
        Jedis jedis = new Jedis(); //如果使用空参构造，默认值 "localhost",6379端口
//        2.操作
        jedis.sadd("mySet","java","C","php","web");
        Set<String> mySet = jedis.smembers("mySet");
        System.out.println(mySet);
//        3.关闭连接
        jedis.close();
    }

    /**
     * SortedSet数据结构
     */
    @Test
    public void test6() {
//        1.获取连接
        Jedis jedis = new Jedis(); //如果使用空参构造，默认值 "localhost",6379端口
//        2.操作
//        sortedset存储
        jedis.zadd("mysortedset",5,"亚瑟");
        jedis.zadd("mysortedset",30,"后羿");
        jedis.zadd("mysortedset",20,"孙悟空");
//        sortedset获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
//        3.关闭连接
        jedis.close();
    }

    /**
     * jedis连接池使用
     */
    @Test
    public void test7() {
//        0.创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);    //最大活动对象数
        config.setMaxIdle(100);  // 最大能够保持idel状态的对象数
//        1.创建jedis连接池对象
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);
//        2.获取连接
        Jedis jedis = jedisPool.getResource();
//        3.使用
        jedis.set("haha","zhangsan");
//        4.关闭
        jedis.close();
    }

    /**
     * jedis连接池工具类使用
     */
    @Test
    public void test8(){
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();
        //3. 使用
        jedis.set("hello","world");
        //4. 关闭 归还到连接池中
        jedis.close();;

    }
}
