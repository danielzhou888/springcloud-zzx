package com.concurrency._12_CompletionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 利用 CompletionService 实现 Dubbo 中的 Forking Cluster
 * Dubbo 中有一种叫做 Forking 的集群模式，这种集群模式下，支持并行地调用多个查询服务，只要有一个成功返回结果，整个服务就可以返回了。
 * 例如你需要提供一个地址转坐标的服务，为了保证该服务的高可用和性能，你可以并行地调用 3 个地图服务商的 API，然后只要有 1 个正确返回了结果 r，
 * 那么地址转坐标这个服务就可以直接返回 r 了。
 * 这种集群模式可以容忍 2 个地图服务商服务异常，但缺点是消耗的资源偏多。
 *
 * 利用 CompletionService 可以快速实现 Forking 这种集群模式，比如下面的示例代码就展示了具体是如何实现的。首先我们创建了一个线程池 executor 、
 * 一个 CompletionService 对象 cs 和一个Future类型的列表 futures，每次通过调用 CompletionService 的 submit() 方法提交一个异步任务，会返回一个 Future 对象，
 * 我们把这些 Future 对象保存在列表 futures 中。
 * 通过调用 cs.take().get()，我们能够拿到最快返回的任务执行结果，只要我们拿到一个正确返回的结果，就可以取消所有任务并且返回最终结果了。
 * @author zhouzhixiang
 * @Date 2020-12-05
 */
public class Main2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Integer> completionService = new ExecutorCompletionService(executorService);

        List<Future<Integer>> futures = new ArrayList<>();

        futures.add(completionService.submit(() -> geocoderByS1()));
        futures.add(completionService.submit(() -> geocoderByS2()));
        futures.add(completionService.submit(() -> geocoderByS3()));

        // 获取最快返回的地图结果
        Integer r = 0;
        try {
            for (int i = 0; i < 3; i++) {
                r = completionService.take().get();
                if (r != null) {
                    break;
                }
            }
        } finally {
            // 取消所有任务
            for (Future<Integer> f : futures) {
                f.cancel(true);
            }
        }

        // return r; 返回结果
    }

    static Integer geocoderByS1() {
        return 1;
    }

    static Integer geocoderByS2() {
        return 2;
    }

    static Integer geocoderByS3() {
        return 3;
    }
}
