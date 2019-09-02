package com.zzx.spring.zzxspring.fallback;

import com.zzx.spring.zzxspring.feign.RemoteSystemAuth;
import feign.hystrix.FallbackFactory;

import java.util.Set;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class RemoteSystemAuthFallbackFactory implements FallbackFactory<RemoteSystemAuth> {
    @Override
    public RemoteSystemAuth create(Throwable throwable) {
        return new RemoteSystemAuth() {
            @Override
            public Set<String> selectAuthsByUserId(Long userId) {
                return null;
            }
        };
    }
}
