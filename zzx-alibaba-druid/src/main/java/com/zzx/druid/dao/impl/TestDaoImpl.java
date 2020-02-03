package com.zzx.druid.dao.impl;

import com.zzx.druid.dao.ITestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-21 16:15
 **/
@Repository("testDao")
public class TestDaoImpl implements ITestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void test() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from test");
        System.out.println(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save() {
        jdbcTemplate.execute("INSERT INTO test ('test_name', 'test_num') VALUES ('zhouzhixiang', '25')");
    }
}
