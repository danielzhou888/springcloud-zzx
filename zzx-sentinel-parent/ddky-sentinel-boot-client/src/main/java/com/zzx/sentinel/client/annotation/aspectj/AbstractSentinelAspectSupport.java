package com.zzx.sentinel.client.annotation.aspectj;

import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.MethodUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.zzx.sentinel.client.annotation.SentinelAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public abstract class AbstractSentinelAspectSupport {
    public AbstractSentinelAspectSupport() {
    }

    protected void traceException(Throwable ex) {
        Tracer.trace(ex);
    }

    protected void traceException(Throwable ex, SentinelAnnotation annotation) {
        Class<? extends Throwable>[] exceptionsToIgnore = annotation.exceptionsToIgnore();
        if (exceptionsToIgnore.length <= 0 || !this.exceptionBelongsTo(ex, exceptionsToIgnore)) {
            if (this.exceptionBelongsTo(ex, annotation.exceptionsToTrace())) {
                this.traceException(ex);
            }

        }
    }

    protected boolean exceptionBelongsTo(Throwable ex, Class<? extends Throwable>[] exceptions) {
        if (exceptions == null) {
            return false;
        } else {
            Class[] var3 = exceptions;
            int var4 = exceptions.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Class<? extends Throwable> exceptionClass = var3[var5];
                if (exceptionClass.isAssignableFrom(ex.getClass())) {
                    return true;
                }
            }

            return false;
        }
    }

    protected String getResourceName(String resourceName, Method method) {
        return StringUtil.isNotBlank(resourceName) ? resourceName : MethodUtil.resolveMethodName(method);
    }

    protected Object handleFallback(ProceedingJoinPoint pjp, SentinelAnnotation annotation, Throwable ex) throws Throwable {
        return this.handleFallback(pjp, annotation.fallback(), annotation.defaultFallback(), annotation.fallbackClass(), ex);
    }

    protected Object handleFallback(ProceedingJoinPoint pjp, String fallback, String defaultFallback, Class<?>[] fallbackClass, Throwable ex) throws Throwable {
        Object[] originArgs = pjp.getArgs();
        Method fallbackMethod = this.extractFallbackMethod(pjp, fallback, fallbackClass);
        if (fallbackMethod != null) {
            int paramCount = fallbackMethod.getParameterTypes().length;
            Object[] args;
            if (paramCount == originArgs.length) {
                args = originArgs;
            } else {
                args = Arrays.copyOf(originArgs, originArgs.length + 1);
                args[args.length - 1] = ex;
            }

            try {
                return this.isStatic(fallbackMethod) ? fallbackMethod.invoke((Object)null, args) : fallbackMethod.invoke(pjp.getTarget(), args);
            } catch (InvocationTargetException var11) {
                throw var11.getTargetException();
            }
        } else {
            return this.handleDefaultFallback(pjp, defaultFallback, fallbackClass, ex);
        }
    }

    protected Object handleDefaultFallback(ProceedingJoinPoint pjp, String defaultFallback, Class<?>[] fallbackClass, Throwable ex) throws Throwable {
        Method fallbackMethod = this.extractDefaultFallbackMethod(pjp, defaultFallback, fallbackClass);
        if (fallbackMethod != null) {
            Object[] args = fallbackMethod.getParameterTypes().length == 0 ? new Object[0] : new Object[]{ex};

            try {
                return this.isStatic(fallbackMethod) ? fallbackMethod.invoke((Object)null, args) : fallbackMethod.invoke(pjp.getTarget(), args);
            } catch (InvocationTargetException var8) {
                throw var8.getTargetException();
            }
        } else {
            throw ex;
        }
    }

    protected Object handleBlockException(ProceedingJoinPoint pjp, SentinelAnnotation annotation, BlockException ex) throws Throwable {
        Method blockHandlerMethod = this.extractBlockHandlerMethod(pjp, annotation.blockHandler(), annotation.blockHandlerClass());
        if (blockHandlerMethod != null) {
            Object[] originArgs = pjp.getArgs();
            Object[] args = Arrays.copyOf(originArgs, originArgs.length + 1);
            args[args.length - 1] = ex;

            try {
                return this.isStatic(blockHandlerMethod) ? blockHandlerMethod.invoke((Object)null, args) : blockHandlerMethod.invoke(pjp.getTarget(), args);
            } catch (InvocationTargetException var8) {
                throw var8.getTargetException();
            }
        } else {
            return this.handleFallback(pjp, annotation, ex);
        }
    }

    private Method extractFallbackMethod(ProceedingJoinPoint pjp, String fallbackName, Class<?>[] locationClass) {
        if (StringUtil.isBlank(fallbackName)) {
            return null;
        } else {
            boolean mustStatic = locationClass != null && locationClass.length >= 1;
            Class<?> clazz = mustStatic ? locationClass[0] : pjp.getTarget().getClass();
            MethodWrapper m = ResourceMetadataRegistry.lookupFallback(clazz, fallbackName);
            if (m == null) {
                Method method = this.resolveFallbackInternal(pjp, fallbackName, clazz, mustStatic);
                ResourceMetadataRegistry.updateFallbackFor(clazz, fallbackName, method);
                return method;
            } else {
                return !m.isPresent() ? null : m.getMethod();
            }
        }
    }

    private Method extractDefaultFallbackMethod(ProceedingJoinPoint pjp, String defaultFallback, Class<?>[] locationClass) {
        if (StringUtil.isBlank(defaultFallback)) {
            SentinelAnnotation annotationClass = (SentinelAnnotation)pjp.getTarget().getClass().getAnnotation(SentinelAnnotation.class);
            if (annotationClass == null || !StringUtil.isNotBlank(annotationClass.defaultFallback())) {
                return null;
            }

            defaultFallback = annotationClass.defaultFallback();
            if (locationClass == null || locationClass.length < 1) {
                locationClass = annotationClass.fallbackClass();
            }
        }

        boolean mustStatic = locationClass != null && locationClass.length >= 1;
        Class<?> clazz = mustStatic ? locationClass[0] : pjp.getTarget().getClass();
        MethodWrapper m = ResourceMetadataRegistry.lookupDefaultFallback(clazz, defaultFallback);
        if (m == null) {
            Class<?> originReturnType = this.resolveMethod(pjp).getReturnType();
            Class<?>[] defaultParamTypes = new Class[0];
            Class<?>[] paramTypeWithException = new Class[]{Throwable.class};
            Method method = this.findMethod(mustStatic, clazz, defaultFallback, originReturnType, defaultParamTypes);
            if (method == null) {
                method = this.findMethod(mustStatic, clazz, defaultFallback, originReturnType, paramTypeWithException);
            }

            ResourceMetadataRegistry.updateDefaultFallbackFor(clazz, defaultFallback, method);
            return method;
        } else {
            return !m.isPresent() ? null : m.getMethod();
        }
    }

    private Method resolveFallbackInternal(ProceedingJoinPoint pjp, String name, Class<?> clazz, boolean mustStatic) {
        Method originMethod = this.resolveMethod(pjp);
        Class<?>[] defaultParamTypes = originMethod.getParameterTypes();
        Class<?>[] paramTypesWithException = (Class[])Arrays.copyOf(defaultParamTypes, defaultParamTypes.length + 1);
        paramTypesWithException[paramTypesWithException.length - 1] = Throwable.class;
        Method method = this.findMethod(mustStatic, clazz, name, originMethod.getReturnType(), defaultParamTypes);
        if (method == null) {
            method = this.findMethod(mustStatic, clazz, name, originMethod.getReturnType(), paramTypesWithException);
        }

        return method;
    }

    private Method extractBlockHandlerMethod(ProceedingJoinPoint pjp, String name, Class<?>[] locationClass) {
        if (StringUtil.isBlank(name)) {
            return null;
        } else {
            boolean mustStatic = locationClass != null && locationClass.length >= 1;
            Class clazz;
            if (mustStatic) {
                clazz = locationClass[0];
            } else {
                clazz = pjp.getTarget().getClass();
            }

            MethodWrapper m = ResourceMetadataRegistry.lookupBlockHandler(clazz, name);
            if (m == null) {
                Method method = this.resolveBlockHandlerInternal(pjp, name, clazz, mustStatic);
                ResourceMetadataRegistry.updateBlockHandlerFor(clazz, name, method);
                return method;
            } else {
                return !m.isPresent() ? null : m.getMethod();
            }
        }
    }

    private Method resolveBlockHandlerInternal(ProceedingJoinPoint pjp, String name, Class<?> clazz, boolean mustStatic) {
        Method originMethod = this.resolveMethod(pjp);
        Class<?>[] originList = originMethod.getParameterTypes();
        Class<?>[] parameterTypes = (Class[])Arrays.copyOf(originList, originList.length + 1);
        parameterTypes[parameterTypes.length - 1] = BlockException.class;
        return this.findMethod(mustStatic, clazz, name, originMethod.getReturnType(), parameterTypes);
    }

    private boolean checkStatic(boolean mustStatic, Method method) {
        return !mustStatic || this.isStatic(method);
    }

    private Method findMethod(boolean mustStatic, Class<?> clazz, String name, Class<?> returnType, Class<?>... parameterTypes) {
        Method[] methods = clazz.getDeclaredMethods();
        Method[] var7 = methods;
        int var8 = methods.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            Method method = var7[var9];
            if (name.equals(method.getName()) && this.checkStatic(mustStatic, method) && returnType.isAssignableFrom(method.getReturnType()) && Arrays.equals(parameterTypes, method.getParameterTypes())) {
                RecordLog.info("Resolved method [{}] in class [{}]", new Object[]{name, clazz.getCanonicalName()});
                return method;
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && !Object.class.equals(superClass)) {
            return this.findMethod(mustStatic, superClass, name, returnType, parameterTypes);
        } else {
            String methodType = mustStatic ? " static" : "";
            RecordLog.warn("Cannot find{} method [{}] in class [{}] with parameters {}", new Object[]{methodType, name, clazz.getCanonicalName(), Arrays.toString(parameterTypes)});
            return null;
        }
    }

    private boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    protected Method resolveMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Method method = this.getDeclaredMethodFor(targetClass, signature.getName(), signature.getMethod().getParameterTypes());
        if (method == null) {
            throw new IllegalStateException("Cannot resolve target method: " + signature.getMethod().getName());
        } else {
            return method;
        }
    }

    private Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException var6) {
            Class<?> superClass = clazz.getSuperclass();
            return superClass != null ? this.getDeclaredMethodFor(superClass, name, parameterTypes) : null;
        }
    }
}
