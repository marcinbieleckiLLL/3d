package com.greencrane.filter;

import com.greencrane.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
@Order(value = 2)
public class CaptchaFilter extends AFilter {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaFilter.class);
    private static final String[] SHOULD_FILTER_LIST = {"/communicate"};

    @Value("${captcha.headerName}")
    String headerName;

    @Override
    protected boolean shouldFilter(HttpServletRequest request) {
        return ListUtils.containsIgnoreCase(Arrays.asList(SHOULD_FILTER_LIST), request.getRequestURI());
    }
    @Override
    protected boolean isValid(String header) {
        return filterUtils.isCaptchaValid(header);
    }
    @Override
    protected String getHeaderName() {
        return headerName;
    }
    @Override
    protected Logger getLogger() {
        return logger;
    }
}
