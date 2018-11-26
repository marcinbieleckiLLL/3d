package com.greencrane.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

@Configuration
@Order(value = 1)
public class SecurityFilter extends AFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Value("${token.name}")
    String tokenName;

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected boolean shouldFilter(HttpServletRequest request) {
        return true;
    }

    @Override
    protected boolean isValid(String header) {
        return filterUtils.isTokenValid(header);
    }

    @Override
    protected String getHeaderName() {
        return tokenName;
    }
}
