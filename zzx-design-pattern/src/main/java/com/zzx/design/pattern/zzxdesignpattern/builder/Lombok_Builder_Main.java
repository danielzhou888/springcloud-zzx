package com.zzx.design.pattern.zzxdesignpattern.builder;

import java.util.Optional;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
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
