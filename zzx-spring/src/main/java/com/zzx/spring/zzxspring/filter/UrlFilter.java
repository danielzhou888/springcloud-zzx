package com.zzx.spring.zzxspring.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * https://blog.csdn.net/m0_38075425/article/details/81164930
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-03
 */
@WebFilter( filterName = "urlFilter", urlPatterns = "/url/*" )
@Log4j2
public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if (!requestURI.contains("info")) {
            servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("destory...");
    }
}
