//package com.zzx.tools.example;
//
//import com.zzx.tools.base.ServiceResponse;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
///**
// * @author zhouzhixiang
// * @company 叮当快药科技集团有限公司
// * @Date 2020-02-19
// */
//@Controller
//@RequestMapping("test")
//public class TestController {
//
//    @Autowired
//    private TestService testService;
//
//    @RequestMapping("query")
//    public ServiceResponse query(TestExample example, int page, int rows) {
//        TestExample.Criteria criteria = example.createCriteria();
//        String gbNo = example.getGbNo();
//        String gbName = example.getGbName();
//        Long status = example.getStatus();
//        if (StringUtils.isNotEmpty(gbNo)) {
//            criteria.andGbNoLike(gbNo);
//        }
//        if (StringUtils.isNotEmpty(gbName)) {
//            criteria.andGbNameLike(gbName);
//        }
//        if (status != null && (status == 0 || status == 1)) {
//            criteria.andStatusEqualTo(status);
//        }
//        example.setPage(page);
//        example.setRows(rows);
//        List<TestEntity> testEntities = testService.selectByExample(example);
//        ServiceResponse response = new ServiceResponse();
//        response.setCode(0);
//        response.setMsg("success");
//        response.setData(testEntities);
//        return response;
//    }
//}
