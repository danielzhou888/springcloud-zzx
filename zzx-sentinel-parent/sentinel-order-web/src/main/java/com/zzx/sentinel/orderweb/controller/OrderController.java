package com.zzx.sentinel.orderweb.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.zzx.sentinel.client.annotation.SentinelAnnotation;
import com.zzx.sentinel.client.util.SentinelResoucesNamer;
import com.zzx.sentinel.distribute.enums.ResponseEnum;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.order.api.OrderApi;
import com.zzx.sentinel.orderweb.sentinel.OrderControllerSentinell;
import com.zzx.sentinel.orderweb.utils.OrderUtils;
import com.zzx.sentinel.voucher.api.VoucherApi;
import com.zzx.sentinel.wdc.api.WdcApi;
import com.zzx.sentinel.wdc.po.Distribute;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    //@Reference
    @DubboReference
    private WdcApi wdcApi;

    @DubboReference
    private VoucherApi voucherApi;

    @DubboReference
    private OrderApi orderApi;

    /**
     * 模拟下订单调用配送系统限流
     * @return
     * @throws Exception
     */
    @SentinelAnnotation(value = "createOrderSentinel", fallback = "createOrderFallback")
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

    @RequestMapping("/createOrder2")
    @ResponseBody
    public String createOrder2(@RequestParam("userId") Long userId) throws Exception {
        String result = "";
        String orderCode = OrderUtils.getOrderCode(userId);
        ServiceResponse<Distribute> response = null;
        try (Entry entry = SphU.entry("wdcApi-distribute-sentinel")) {
            response = wdcApi.distribute(orderCode, userId);
            result = JSONObject.toJSONString(response);
        } catch (BlockException e) {
            log.error("createOrder2 出现流控异常了", e);
            result = this.createOrderFallback(userId);
        }

        return result;
    }

    @RequestMapping("/createOrder3")
    @ResponseBody
    public String createOrder3(@RequestParam("userId") Long userId) throws Exception {
        String result = "";
        String orderCode = OrderUtils.getOrderCode(userId);
        ServiceResponse<Distribute> response = null;
        Entry entry = null;
        try {
            entry = SphU.entry("wdcApi-distribute-sentinel3");
            response = wdcApi.distribute(orderCode, userId);
            result = JSONObject.toJSONString(response);
        } catch (BlockException e) {
            log.error("createOrder2 出现流控异常了", e);
            result = this.createOrderFallback(userId);
        } catch (Exception ex) {
            log.error("createOrder3 occur exception {}", ex);
            Tracer.traceEntry(ex, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return result;
    }

    /**
     * 模拟提交订单异步调用链路的降级 -> 手动处理调用api降级逻辑
     * @param userId
     * @return
     * @throws Exception
     */
    @SentinelAnnotation(value = "orderController-createOrderAsync-sentinel", fallbackClass = OrderControllerSentinell.class, fallback = "createOrderAsyncSentinel")
    @RequestMapping("/createOrderAsync")
    @ResponseBody
    public ServiceResponse createOrderAsync(@RequestParam("userId") Long userId) throws Exception {
        ServiceResponse response;
        try {
            response = orderApi.createOrderAsync(userId);
        } catch (Exception e) {
            log.error("OrderController createOrderAsync exception {}", e);
            response = new ServiceResponse(ResponseEnum.SUBMIT_ORDER_EXCEPTION.getCode(), ResponseEnum.SUBMIT_ORDER_EXCEPTION.getName());
        }
        return response;
    }

    /**
     * 模拟提交订单同步调用链路的降级 -> 手动处理调用api降级逻辑
     * @param userId
     * @return
     * @throws Exception
     */
    @SentinelAnnotation(value = "orderWeb-orderController.createOrderSync-sentinel", fallbackClass = OrderControllerSentinell.class, fallback = "createOrderSyncSentinel")
    @RequestMapping("/createOrderSync")
    @ResponseBody
    public ServiceResponse createOrderSync(@RequestParam("userId") Long userId) throws Exception {
        ServiceResponse response;
        try {
            response = orderApi.createOrderSync(userId);
        } catch (Exception e) {
            log.error("OrderController createOrderAsync exception {}", e);
            response = new ServiceResponse(ResponseEnum.SUBMIT_ORDER_EXCEPTION.getCode(), ResponseEnum.SUBMIT_ORDER_EXCEPTION.getName());
        }
        return response;
    }

    @RequestMapping("/globalBlockTest")
    @ResponseBody
    public ServiceResponse globalBlockTest() throws Exception {
        ServiceResponse response = new ServiceResponse();
        try {
            response = orderApi.globalBlockMethod();
        } catch (Exception e) {
            log.error("OrderController createOrderAsync exception {}", e);
            response.fail();
        }
        return response;
    }

    /**
     * 测试@SentinelAnnotation注解
     * 通过自定义注解实现资源名拼接 统一资源名格式
     * 系统名 + 类名 + 方法名 + sentinel
     * @return
     * @throws Exception
     */
    @SentinelAnnotation(value = "testSentinelAnnotation", fallbackClass = OrderControllerSentinell.class, fallback = "testSentinelAnnotationSentinel")
    @RequestMapping("/testSentinelAnnotation")
    @ResponseBody
    public ServiceResponse testSentinelAnnotation() throws Exception {
        ServiceResponse response = new ServiceResponse();
        log.info("testSentinelAnnotation success");
        return response;
    }


    /**
     * 测试使用SentinelResoucesNamer.resourceName获取统一格式资源名
     * @return
     * @throws Exception
     */
    @RequestMapping("/testUseSentinelResourceNamer")
    @ResponseBody
    public ServiceResponse testUseSentinelResourceNamer() throws Exception {
        ServiceResponse response = new ServiceResponse();
        Entry entry = null;
        try {
            entry = SphU.entry(SentinelResoucesNamer.resourceName(VoucherApi.class, "testUseSentinelResourceNamer"));
            response = voucherApi.testUseSentinelResourceNamer();
        } catch (BlockException e) {
            // 执行本地降级/限流逻辑
            // TODO
        } catch (Exception e) {
            // 异常处理
            log.error("....");
            Tracer.traceEntry(e, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return response;
    }


    public String createOrderFallback(@RequestParam("userId") Long userId) {
        log.info("createOrderFallback 执行降级");
        return "createOrderFallback 执行降级";
    }

    @SentinelAnnotation(value = "aToBOrToCOrToDFallbackLevel1", blockHandler = "testAToBOrToCOrToDBlockHandlerLevel2")
    //@SentinelAnnotation(value = "aToBOrToCOrToDFallbackLevel1", fallback = "testAToBOrToCOrToDFallbackLevel2")
    @RequestMapping("/testAToBOrToCOrToDFallbackLevel1")
    @ResponseBody
    public String testAToBOrToCOrToDFallbackLevel1(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) {
        String result = "";
        try {
            log.info("我是正常逻辑");
            //result = wdcApi.testToCThrowsException();
        } catch (Exception e) {
            log.error("testAToBOrToCOrToDFallbackLevel1 error {}", e);
            //result = testAToBOrToCOrToDFallbackLevel2();
        }
        //int i = 1/0;
        return result;
    }

    public String testAToBOrToCOrToDFallbackLevel2(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) {
        log.info("testAToBOrToCOrToDFallbackLevel2 我是兜底逻辑");
        return "testAToBOrToCOrToDFallbackLevel2 我是兜底逻辑";
    }

    public static String testAToBOrToCOrToDBlockHandlerLevel2(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, BlockException e) {
        log.info("testAToBOrToCOrToDBlockHandlerLevel2 我是兜底逻辑");
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
    @SentinelAnnotation(value = "limitQpsTest2Sentinel", blockHandler = "limitQpsTestBlockHandler2")
    @GetMapping("/limitQpsTest2")
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
    @SentinelAnnotation(value = "hotParameterLimitTestSentinel", blockHandler = "hotParameterLimitTestBlockHandler")
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
    @SentinelAnnotation(value = "authorizeBlockTest2Sentinel", blockHandler = "authorizeBlockTest2BlockHandler")
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

    @RequestMapping("/testDefaultMachine")
    @ResponseBody
    public ServiceResponse testDefaultMachine() {
        return this.voucherApi.testDefaultMachine();
    }

    @RequestMapping("/testDefaultMachineFromOrderInvoke")
    @ResponseBody
    public ServiceResponse testDefaultMachineFromOrderInvoke() {
        return this.orderApi.testDefaultMachine();
    }

    @RequestMapping("/testGlobalFallbackHandler")
    @ResponseBody
    public ServiceResponse testGlobalFallbackHandler() {
        return this.orderApi.testGlobalFallbackHandler();
    }
}