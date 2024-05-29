package org.zzx.nk.lambda.lambda_bifunction01;

import java.util.function.BiFunction;

/**
 * 案例2：合并用户信息
 * 问题：使用 BiFunction 合并用户的姓名和地址。
 * 风格：将用户的名字和地址拼接成一个完整的信息字符串。
 */
public class UserInfoMerger {
    public static void main(String[] args) {
        BiFunction<String, String, String> mergeUserInfo = (name, address) -> "Name: " + name + ", Address: " + address;

        String name = "John Doe";
        String address = "1234 Elm Street";
        String userInfo = mergeUserInfo.apply(name, address);

        System.out.println(userInfo);

        String finalStr = mergeStr((s1, s2) -> "Name: " + s1 + ", Address: " + s2, name, address);
        System.out.println(finalStr);
    }

    public static String mergeStr(BiFunction<String, String, String> biFunction, String str1, String str2) {
        return biFunction.apply(str1, str2);
    }
}