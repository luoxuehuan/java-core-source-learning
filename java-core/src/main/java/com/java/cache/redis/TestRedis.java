package com.java.cache.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;


/**
 * 
 * @author lxh
 * 
 * redis的安装：
 * sudo apt-get update$sudo apt-get install redis-server
 *
 * 启动：
 * redis-server
 * 
 * 连接客户端：
 * redis-cli
 * 
 * 教程地址：
 * http://www.yiibai.com/redis/redis_quick_guide.html
 * 
 * 官网实战地址：
 * http://try.redis.io/
 * 
 */
public class TestRedis {
    private Jedis jedis; 
    
    @Before
    public void setup() {
        //连接redis服务器，192.168.0.100:6379
        jedis = new Jedis("10.137.12.12", 6379);
        //权限认证
       // jedis.auth("admin");  
    }
    
    /**
     * redis存储字符串
     */
    @Test
    public void testString() {
        //-----添加数据----------  
        jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin  
        System.out.println(jedis.get("name"));//执行结果：xinxin  
        
        jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name")); 
        
        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name","liuling","age","23","qq","476777XXX");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
        
    }
    
    /**
     * redis操作Map
     */
    @Test
    public void testMap() {
        //-----添加数据----------  
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "spark");
        map.put("age", "4");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);  
  
        //删除map中的某个键值  
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true  
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value 
  
        Iterator<String> iter=jedis.hkeys("user").iterator();  
        while (iter.hasNext()){  
            String key = iter.next();  
            System.out.println(key+":"+jedis.hmget("user",key));  
        }  
    }
    
    /** 
     * jedis操作List 
     */  
    @Test  
    public void testList(){  
        //开始前，先移除所有的内容  
        jedis.del("big data");  
        System.out.println(jedis.lrange("big data",0,-1));  
        //先向key java framework中存放三条数据  
        jedis.lpush("big data","hadoop");  
        jedis.lpush("big data","flink");  
        jedis.lpush("big data","spark");  
        //再取出所有数据jedis.lrange是按范围取出,
        //第一个是key,第二个是起始位置,第三个是结束位置,jedis.llen获取长度 -1表示取得所有  
        System.out.println(jedis.lrange("java framework",0,-1));  
        
        jedis.del("big data");
        jedis.rpush("big data","spark");  
        jedis.rpush("big data","hadoop");  
        jedis.rpush("big data","scala"); 
        System.out.println(jedis.lrange("big data",0,-1));
    }  
    
    /** 
     * jedis操作Set 
     */  
    @Test  
    public void testSet(){  
        //测试之前先删除
    	jedis.del("data");
        jedis.sadd("data","spark");  
        jedis.sadd("data","scala");  
        jedis.sadd("data","hadoop");  
        jedis.sadd("data","kafka");
        jedis.sadd("data","Zookeeper");  
        //移除noname  
        jedis.srem("data","kafka");  
        System.out.println("获取所有加入的value		"+jedis.smembers("data"));//获取所有加入的value  
        System.out.println("判断 hadoop 是否是data集合的元素		"+jedis.sismember("data", "hadoop"));//判断 hadoop 是否是data集合的元素  
        System.out.println("随机获取一个元素		"+jedis.srandmember("data"));  
        System.out.println("返回集合的元素个数		"+jedis.scard("data"));//返回集合的元素个数  
    }  
  
    @Test  
    public void testSort() throws InterruptedException {  
        //jedis 排序  
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）  
        jedis.del("a");//先清除数据，再加入数据进行测试  
        jedis.rpush("a", "1");  
        jedis.lpush("a","6");  
        jedis.lpush("a","3");  
        jedis.lpush("a","9");  
        System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]  
        System.out.println("输出排序后结果 "+jedis.sort("a")); //[1, 3, 6, 9]  
        System.out.println(jedis.lrange("a",0,-1));  
    }  
    
    @Test
    public void testRedisPool() {
        RedisUtil.getJedis().set("newname", "中文测试");
        System.out.println(RedisUtil.getJedis().get("newname"));
    }
}