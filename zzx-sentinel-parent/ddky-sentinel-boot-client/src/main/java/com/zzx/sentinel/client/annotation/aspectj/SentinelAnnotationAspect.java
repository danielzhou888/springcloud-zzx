package com.zzx.sentinel.client.annotation.aspectj;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zzx.sentinel.client.annotation.SentinelAnnotation;
import com.zzx.sentinel.client.util.SentinelResoucesNamer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * Sentinel Annotation 切面类 <br>
 * <pre>
 *     自定义实现，用于简化资源名命名
 *     资源名：系统名 + 类名 + 方法名 + sentinel
 * </pre>
 * @author zhouzhixiang
 * @Date 2020-12-07
 */
@Aspect
public class SentinelAnnotationAspect extends AbstractSentinelAspectSupport {
    public SentinelAnnotationAspect() {
    }

    @Pointcut("@annotation(com.zzx.sentinel.client.annotation.SentinelAnnotation)")
    public void sentinelResourceAnnotationPointcut() {
    }

    @Around("sentinelResourceAnnotationPointcut()")
    public Object invokeResourceWithSentinel(ProceedingJoinPoint pjp) throws Throwable {
        Method originMethod = this.resolveMethod(pjp);
        SentinelAnnotation annotation = (SentinelAnnotation)originMethod.getAnnotation(SentinelAnnotation.class);
        if (annotation == null) {
            throw new IllegalStateException("Wrong state for SentinelAnnotation annotation");
        } else {
            String resourceName = SentinelResoucesNamer.resourceName(originMethod.getDeclaringClass(), this.getResourceName(annotation.value(), originMethod));
            EntryType entryType = annotation.entryType();
            int resourceType = annotation.resourceType();
            Entry entry = null;

            try {
                Object var18;
                try {
                    entry = SphU.entry(resourceName, resourceType, entryType, pjp.getArgs());
                    Object result = pjp.proceed();
                    var18 = result;
                    return var18;
                } catch (BlockException var15) {
                    var18 = this.handleBlockException(pjp, annotation, var15);
                    return var18;
                } catch (Throwable var16) {
                    Class<? extends Throwable>[] exceptionsToIgnore = annotation.exceptionsToIgnore();
                    if (exceptionsToIgnore.length > 0 && this.exceptionBelongsTo(var16, exceptionsToIgnore)) {
                        throw var16;
                    } else if (this.exceptionBelongsTo(var16, annotation.exceptionsToTrace())) {
                        this.traceException(var16);
                        Object var10 = this.handleFallback(pjp, annotation, var16);
                        return var10;
                    } else {
                        throw var16;
                    }
                }
            } finally {
                if (entry != null) {
                    entry.exit(1, pjp.getArgs());
                }

            }
        }
    }
}
