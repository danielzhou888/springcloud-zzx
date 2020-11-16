package com.zzx.sentinel.orderweb.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.orderweb.utils.OrderUtils;
import com.zzx.sentinel.wdc.api.WdcApi;
import com.zzx.sentinel.wdc.po.Distribute;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    //@Reference
    //@DubboReference
    private WdcApi wdcApi;

    /**
     * 模拟下订单调用配送系统限流
     * @return
     * @throws Exception
     */
    //@SentinelResource(value = "createOrderSentinel", fallback = "testAToBOrToCOrToDFallbackLevel1")
    @RequestMapping("/createOrder")
    @ResponseBody
    public String createOrder(@RequestParam("userId") Long userId) throws Exception {
        String result = "";
        try {
            String orderCode = OrderUtils.getOrderCode(userId);
            ServiceResponse<Distribute> response = wdcApi.distribute(orderCode, userId);
            if (response == null || response.isDownGrade()) {
                // 降级了
                // ...
                result = "wdc 降级了: " + JSONObject.toJSONString(response);
            } else {
                result = JSONObject.toJSONString(response);
            }
        } catch (Exception e) {
            log.error("createOrder error = {}", e);
            // ....
        }

        return result;
    }

    @SentinelResource(value = "aToBOrToCOrToDFallbackLevel1", fallback = "testAToBOrToCOrToDFallbackLevel2")
    @RequestMapping("/testAToBOrToCOrToDFallbackLevel1")
    @ResponseBody
    public String testAToBOrToCOrToDFallbackLevel1() throws Exception {
        String result = "";
        try {
            result = wdcApi.testToCThrowsException();
        } catch (Exception e) {
            log.error("testAToBOrToCOrToDFallbackLevel1 error {}", e);
            result = testAToBOrToCOrToDFallbackLevel2();
        }
        return result;
    }

    public String testAToBOrToCOrToDFallbackLevel2() {
        return "testAToBOrToCOrToDFallbackLevel2 我是兜底逻辑";
    }

    /**
     * 流控测试
     * @return
     * @throws Exception
     */
    @RequestMapping("/limitQpsTest")
    @ResponseBody
    public String limitQpsTest() throws Exception {
        //log.info("limitQpsTest 流控测试正常逻辑");
        //return "limitQpsTest 流控测试正常逻辑";
        return wdcApi.limitQpsTest();
    }

    /**
     * 流控测试
     * @return
     * @throws Exception
     */
    @SentinelResource(value = "limitQpsTest2", blockHandler = "limitQpsTestBlockHandler2")
    @RequestMapping("/limitQpsTest2")
    @ResponseBody
    public String limitQpsTest2() throws Exception {
        log.info("limitQpsTest2 流控测试正常逻辑");
        return "limitQpsTest2 流控测试正常逻辑";
    }


    public static String limitQpsTestBlockHandler2(BlockException e) throws Exception {
        log.info("limitQpsTest2 流控测试流控逻辑 = e:"+e.getMessage());
        return "limitQpsTest2 流控测试流控逻辑 = e:"+e.getMessage();
    }

    /**
     * 热点参数限流测试
     * @return
     * @throws Exception
     */
    @SentinelResource(value = "hotParameterLimitTestSentinel", blockHandler = "hotParameterLimitTestBlockHandler")
    @RequestMapping("/hotParameterLimitTest")
    @ResponseBody
    public String hotParameterLimitTest(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("age") int age) throws Exception {
        log.info("username: "+username+" password: "+password+" age: "+age);
        return "username: "+username+" password: "+password+" age: "+age;
    }

    /**
     * 热点参数降级回调函数
     * @return
     * @throws Exception
     */
    public String hotParameterLimitTestBlockHandler(String username, String password, int age, BlockException e) throws Exception {
        log.info("username: "+username+" password: "+password+" age: "+age+" e: "+e.getClass().getSimpleName());
        return "username: "+username+" password: "+password+" age: "+age+" e: "+e.getClass().getSimpleName();
    }

    /**
     * 授权限流测试
     * @return
     * @throws Exception
     */
    @RequestMapping("/authorizeBlockTest")
    @ResponseBody
    public String authorizeBlockTest() throws Exception {
        return wdcApi.authorizeBlockTest();
    }

    /**
     * 授权限流测试
     * @return
     * @throws Exception
     */
    @RequestMapping("/authorizeBlockTest2")
    @SentinelResource(value = "authorizeBlockTest2Sentinel", blockHandler = "authorizeBlockTest2BlockHandler")
    @ResponseBody
    public String authorizeBlockTest2() throws Exception {
        String result = "authorizeBlockTest2 run";
        log.info(result);
        return result;
    }

    /**
     * 授权限流回调函数
     * @return
     * @throws Exception
     */
    public String authorizeBlockTest2BlockHandler(BlockException e) throws Exception {
        log.info("authorizeBlockTest2BlockHandler e: "+e.getClass().getSimpleName());
        return "authorizeBlockTest2BlockHandler e: "+e.getClass().getSimpleName();
    }

}