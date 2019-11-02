package com.zzx.design.pattern.zzxdesignpattern.functional_interface.chapter2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-31
 */
@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    public ServiceResponse getUserPages() {
        PageCountHelper countHelper = (Query query) userDao.pageCountA(query);
        PageListHelper listHelper = (Query query) userDao.pageListA(query);

        if (type == 1) {
            countHelper = (Query query) userDao.pageCountB(query);
            listHelper = (Query query) userDao.pageListB(query);
        }
        Long total = countHelper.pageCount(query);
        if (total == 0) {
            return new ServiceResponse();
        }
        List<Result> results = listHelper.pageQuery(query);
        return new ServiceResponse().code(200).msg("success").data(results).count(total);
    }
}
