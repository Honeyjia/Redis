package cn.itcast.jedis.test.dao;

import cn.itcast.jedis.test.domain.Province;

import java.util.List;

public interface provinceDao {
    public List<Province> findAll();//返回查询的的结果list集合
}
