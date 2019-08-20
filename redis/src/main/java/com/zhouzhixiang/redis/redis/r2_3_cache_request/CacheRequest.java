package com.zhouzhixiang.redis.redis.r2_3_cache_request;

import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 网页缓存：使用redis缓存来减少载入不常改变页面所需的时间
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-14 18:58
 **/
public class CacheRequest implements Filter {

    private Logger logger = LoggerFactory.getLogger(CacheRequest.class);
    private ApplicationContext applicationContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI().toString();
        String query = request.getQueryString();

        String cacheKey = "";
        if (!StringUtils.isEmpty(query)) {
            cacheKey = uri + "?" + query;
        } else {
            cacheKey = uri;
        }
        logger.info(String.format("当前请求被缓存%s", cacheKey));
        String pageContent = getHtmlFromCache(cacheKey);
        if (StringUtils.isEmpty(pageContent)) {
            logger.info("缓存不存在，生成缓存");
            ResponseWrapper responseWrapper = new ResponseWrapper(response);
            filterChain.doFilter(servletRequest, responseWrapper);
            pageContent = responseWrapper.getResult();
            putIntoCache(cacheKey, pageContent);
        }
        response.setContentType("text/html;charset=utf=8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(pageContent);
    }

    private void putIntoCache(String cacheKey, String pageContent) {
        RedisUtil redisUtil = applicationContext.getBean(RedisUtil.class);
        redisUtil.set(cacheKey, pageContent);
    }

    private String getHtmlFromCache(String cacheKey) {
        RedisUtil redisUtil = applicationContext.getBean(RedisUtil.class);
        return redisUtil.get(cacheKey);
    }

    @Override
    public void destroy() {

    }

}
