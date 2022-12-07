package com.coocon.portal.demo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
public class DebugFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.debug(httpServletRequest.getRequestURI(), ", Method :", httpServletRequest.getMethod());
        log.debug("==================== HEADER ====================");
        Enumeration<String> enu = httpServletRequest.getHeaderNames();
        if (enu != null)
        {
            Object key;
            while(enu.hasMoreElements())
            {
                key = enu.nextElement();
                log.debug("key = [{}] value = [{}]",key.toString(), httpServletRequest.getHeader(key.toString()));
            }
        }
        log.debug("================================================");
        log.debug("==================== ATTRIBUTE ====================");
        enu = httpServletRequest.getAttributeNames();
        if (enu != null)
        {
            Object key;
            while(enu.hasMoreElements())
            {
                key = enu.nextElement();
                log.debug("key = [{}] value = [{}]",key.toString(), httpServletRequest.getAttribute(key.toString()));
            }
        }
        log.debug("===================================================");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}