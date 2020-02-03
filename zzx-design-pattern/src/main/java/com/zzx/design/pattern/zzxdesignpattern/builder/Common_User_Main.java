package com.zzx.design.pattern.zzxdesignpattern.builder;

import java.util.Optional;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class Common_User_Main {
    public static void main(String[] args) {
        Common_User user = new Common_User.Builder().
            username("zhouzhixiang")
            .password("111111")
            .age(22)
            .sex(1)
            .build();
        Optional.of(user).ifPresent(System.out::println);
    }
}
