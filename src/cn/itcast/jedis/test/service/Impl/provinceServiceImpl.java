package cn.itcast.jedis.test.service.Impl;

import cn.itcast.jedis.test.dao.Impl.provinceDaoImpl;
import cn.itcast.jedis.test.dao.provinceDao;
import cn.itcast.jedis.test.domain.Province;
import cn.itcast.jedis.test.service.provinceService;
import cn.itcast.jedis.test.util.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class provinceServiceImpl implements provinceService {
//    1.声明dao
    private provinceDao dao = new provinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    //    使用Redis缓存
    @Override
    public String findAllJson()  {
//        1.先从redis中查询数据
//        1.1获取客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
//        2.判断province_json是否为null空
        if(province_json == null || province_json.length() == 0){
            //没有缓存  redis中没有数据
            System.out.println("redis中没有数据，查询数据库");
//            2.1 从数据库中查询 dao
            List<Province> list = dao.findAll();
//            2.2将list转化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
//            2.3将json数据存入redis
            jedis.set("province",province_json);
//            3.关闭 归还连接
            jedis.close();
        }else {
            System.out.println("redis中有数据，查询缓存");
        }
        return province_json;
    }
}
