package com.zzx.design.pattern.zzxdesignpattern.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
@Data
@AllArgsConstructor
@Builder
public class Lombok_Builder_User {
    private String username;
    private String password;
    private Integer age;
    private Integer sex;
}
