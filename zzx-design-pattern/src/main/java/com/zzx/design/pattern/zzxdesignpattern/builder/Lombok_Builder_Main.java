package com.zzx.design.pattern.zzxdesignpattern.builder;

import java.util.Optional;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class Lombok_Builder_Main {

    public static void main(String[] args) {
        Lombok_Builder_User user = Lombok_Builder_User.builder()
            .age(25)
            .username("zhouzhixiang")
            .password("123456")
            .build();
        Optional.of(user).ifPresent(System.out::println);
    }
}
