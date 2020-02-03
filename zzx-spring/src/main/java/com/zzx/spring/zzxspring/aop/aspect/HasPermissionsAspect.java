package com.zzx.spring.zzxspring.aop.aspect;

import com.zzx.spring.zzxspring.aop.annotation.HasPermissions;
import com.zzx.spring.zzxspring.exception.ForbiddenException;
import com.zzx.spring.zzxspring.feign.RemoteSystemAuthFeign;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
@Component
@Slf4j
@Aspect
public class HasPermissionsAspect {

    @Autowired
    private RemoteSystemAuthFeign remoteSystemAuthFeign;

    @Around("@annotation(com.zzx.spring.zzxspring.aop.annotation.HasPermissions)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        HasPermissions annotation = signature.getClass().getAnnotation(HasPermissions.class);
        if (annotation == null) {
            return point.proceed();
        }
        String auth = new StringBuilder(annotation.value()).toString();
        if (hasAuth(auth)) {
            return point.proceed();
        } else {
            throw new ForbiddenException();
        }

    }

    private boolean hasAuth(String auth) {
        ServletRequestAttributes requestAttributes =
            (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String currentUserId = request.getHeader("currentUserId");
        if (Optional.ofNullable(currentUserId).isPresent()) {
            Long userId = Long.parseLong(currentUserId);
            if (userId == 1L) {
                return true;
            }
            return remoteSystemAuthFeign.selectAuthsByUserId(userId).stream().anyMatch(auth::equals);
        }
        return false;
    }
}
