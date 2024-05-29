package org.zzx.nk.lambda.lambda_supplier01;

import java.util.function.Supplier;

/**
 * 案例4：REST API 客户端
 * 在 REST API 客户端中，我们可以使用 Supplier 来延迟初始化客户端实例，只有在需要时才创建客户端。
 */
public class RestClient {
    private Supplier<HttpClient> clientSupplier = this::createClient;
    private HttpClient client;

    private HttpClient createClient() {
        // 模拟创建 HTTP 客户端
        System.out.println("Creating HTTP client...");
        return new HttpClient();
    }

    public HttpClient getClient() {
        if (client == null) {
            client = clientSupplier.get();
        }
        return client;
    }

    public static void main(String[] args) {
        RestClient restClient = new RestClient();
        // 第一次访问时创建客户端
        HttpClient client1 = restClient.getClient();
        // 后续访问使用已创建的客户端
        HttpClient client2 = restClient.getClient();
    }
}

class HttpClient {
    // 模拟 HTTP 客户端类
}