package com.greencrane.filter;

import com.greencrane.utils.FilterUtils;
import com.greencrane.utils.RequestUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AFilter implements Filter {

    protected abstract Logger getLogger();
    protected abstract boolean shouldFilter(HttpServletRequest request);
    protected abstract boolean isValid(String header);
    protected abstract String getHeaderName();


    @Autowired
    protected RequestUtils requestUtils;
    @Autowired
    protected FilterUtils filterUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        getLogger().info("filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String headerValue = requestUtils.getRequestHeader(request, getHeaderName());
        getLogger().info("header = " + headerValue);

        if(shouldFilter(request)) {
            if (filterUtils.isPreflightCorsRequest(request) || isValid(headerValue))
                success(servletRequest, servletResponse, filterChain);
            else error(servletResponse);
        } else success(servletRequest, servletResponse, filterChain);
    }

    private void success(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        getLogger().info("valid request");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void error(ServletResponse servletResponse) {
        getLogger().warn("not valid request");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public void destroy() {
        getLogger().info("filter destroyed");
    }
}
