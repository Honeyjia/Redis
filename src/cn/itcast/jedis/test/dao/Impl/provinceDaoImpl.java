package cn.itcast.jedis.test.dao.Impl;

import cn.itcast.jedis.test.dao.provinceDao;
import cn.itcast.jedis.test.domain.Province;
import cn.itcast.jedis.test.util.JDBCUtils;
import cn.itcast.jedis.test.util.JedisPoolUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

import java.util.List;

public class provinceDaoImpl implements provinceDao {
//    声明成员变量 jdbctemplate
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findAll() {
//        1.定义sql
        String sql = "SELECT * FROM province";
//        2.执行sql
        List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;
    }
}