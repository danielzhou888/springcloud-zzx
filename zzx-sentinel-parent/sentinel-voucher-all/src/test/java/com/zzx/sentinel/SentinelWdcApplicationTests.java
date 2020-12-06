//package com.zzx.sentinel;
//
//import com.zzx.sentinel.distribute.response.ServiceResponse;
//import com.zzx.sentinel.voucher.api.VoucherApi;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.junit.Test;
//import org.omg.CORBA.TIMEOUT;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.security.auth.callback.Callback;
//import javax.xml.ws.Service;
//import java.util.concurrent.*;
//
//@SpringBootTest
//class SentinelWdcApplicationTests {
//
//    //@Test
//    //void contextLoads() {
//    //}
//
//    @DubboReference
//    private VoucherApi voucherApi;
//
//    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque(100));
//
//    @Test
//    public void test() {
//
//        Future<ServiceResponse> promotionFuture = threadPoolExecutor.submit(new PromotionTask(1L, "1"));
//
//        ServiceResponse promotionResp = new ServiceResponse();
//        try {
//            promotionResp = promotionFuture.get(5, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            // 线程中断处理  -》 抛回MQ/Redis
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            // 异常处理 -》 抛回MQ/Redis
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//            // 请求超时处理 -》 入库
//        }
//
//    }
//
//
//    public class PromotionTask implements Callable<ServiceResponse> {
//
//        private Long userId;
//        private String orderCode;
//
//        public PromotionTask(final Long userId, final String orderCode) {
//            this.userId = userId;
//            this.orderCode = orderCode;
//        }
//
//        @Override
//        public ServiceResponse call() throws Exception {
//
//            ServiceResponse response = new ServiceResponse();
//            try {
//                boolean b = voucherApi.executePromotion(userId, orderCode);
//                // 处理订单
//                // ...
//                response.setData(b);
//                return response;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//}
