package cn.itcast.jedis.test.service;

import cn.itcast.jedis.test.domain.Province;

import java.util.List;

public interface provinceService {
    public List<Province> findAll();
    public String findAllJson();
}
