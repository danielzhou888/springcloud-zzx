package org.zzx.nk.lambda.lambda_function01;

import java.util.function.Function;

/**
 * 案例1：用户注册信息转换
 * 问题：使用 Function 将用户注册信息转换为内部用户对象。
 * 风格：从用户输入的注册信息生成用户对象。
 */
public class UserRegistration {
    public static void main(String[] args) {
        Function<String, User> registrationToUser = registration -> {
            String[] details = registration.split(",");
            return new User(details[0], details[1], details[2]);
        };

        String registration = "john_doe,johndoe@example.com,password123";
        User user = registrationToUser.apply(registration);

        System.out.println(user);
    }

    public void test() {
        String registration = "john_doe,johndoe@example.com,password123";
        User user = this.registerUser(registration);
        System.out.println(user);
    }

    private User registerUser(String registration) {
        return commonFunction((register) -> {
            String[] details = register.split(",");
            return new User(details[0], details[1], details[2]);
        }, registration);
    }

    private User commonFunction(Function<String, User> function, String str) {
        return function.apply(str);
    }
}

class User {
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}