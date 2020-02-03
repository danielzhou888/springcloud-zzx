package com.zzx.druid.service.impl;

import com.zzx.druid.dao.ITestDao;
import com.zzx.druid.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-21 16:20
 **/
@Service("testService")
public class TestServiceImpl implements ITestService {

    @Autowired
    ITestDao testDao;

    @Override
    public void test() {
        testDao.test();
    }

    @Transactional
    @Override
    public void testTransaction() throws Exception {
        testDao.save();
        int i = 9 / 0;
        testDao.save();
    }
}
