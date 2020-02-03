package com.zzx.spring.zzxspring.fallback;

import com.zzx.spring.zzxspring.feign.RemoteSystemAuthFeign;
import feign.hystrix.FallbackFactory;

import java.util.Set;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class RemoteSystemAuthFallbackFactory implements FallbackFactory<RemoteSystemAuthFeign> {
    @Override
    public RemoteSystemAuthFeign create(Throwable throwable) {
        return new RemoteSystemAuthFeign() {
            @Override
            public Set<String> selectAuthsByUserId(Long userId) {
                return null;
            }
        };
    }
}
