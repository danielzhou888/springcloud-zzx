package org.zzx.nk.lambda.lambda_bifunction01;

import java.util.function.BiFunction;
import java.util.Base64;

/**
 * 案例4：生成用户凭证
 * 问题：使用 BiFunction 生成用户凭证。
 * 风格：通过传入用户名和密码生成用户凭证字符串。
 */
public class UserCredentialGenerator {
    public static void main(String[] args) {
        BiFunction<String, String, String> generateCredentials = (username, password) -> 
            Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        String username = "john_doe";
        String password = "password123";
        String credentials = generateCredentials.apply(username, password);

        System.out.println("Encoded Credentials: " + credentials);
    }

    public void test() {
        String username = "zhouzhixiang";
        String password = "23332323";
        String credentials = generateCredentials(username, password);


    }

    private String generateCredentials(String username, String password) {
        return commonBiFunction((s1, s2) -> Base64.getEncoder().encodeToString((s1 + ":" + s2).getBytes()), username, password);
    }

    private String commonBiFunction(BiFunction<String, String, String> biFunction, String s1, String s2) {
        return biFunction.apply(s1, s2);
    }
}