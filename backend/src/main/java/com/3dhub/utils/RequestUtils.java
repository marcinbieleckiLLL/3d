package com.greencrane.utils;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface RequestUtils {
    String getRequestHeader(HttpServletRequest request, String name);
    String getRequestIp(HttpServletRequest request);
}

@Service
class RequestUtilsImpl implements RequestUtils {
    private static final String IP_HEADER = "X-FORWARDED-FOR";

    @Override
    public String getRequestHeader(HttpServletRequest request, String name) {
        return request.getHeader(name);
    }

    @Override
    public String getRequestIp(HttpServletRequest request) {
        String ip = getRequestHeader(request, IP_HEADER);
        return StringUtils.isEmpty(ip) ? request.getRemoteAddr() : ip;
    }
}
