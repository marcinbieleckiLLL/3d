package com.greencrane.utils;

import com.greencrane.consts.LogType;
import com.greencrane.controller.CommunicationCtrl;
import com.greencrane.entity.Logs;
import com.greencrane.service.LogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface LoggingUtils {
    Logs createFromRequest(HttpServletRequest request, LogType type);
    void write(Logs logs, Class clazz);
    void logEvent(HttpServletRequest request, LogType type);
}

@Service
class LoggingUtilsImpl implements LoggingUtils {

    @Autowired
    RequestUtils requestUtils;

    @Autowired
    LogsService logsService;

    @Override
    public Logs createFromRequest(HttpServletRequest request, LogType type) {
        String ip = requestUtils.getRequestIp(request);
        String method = request.getMethod();
        String userAgent = requestUtils.getRequestHeader(request, "USER-AGENT");
        return new Logs(ip, method, userAgent, type);
    }

    @Override
    public void write(Logs logs, Class clazz) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(logs.toString());
    }

    @Override
    public void logEvent(HttpServletRequest request, LogType type) {
        Logs logs = createFromRequest(request, type);
        logs = logsService.save(logs);
        write(logs, CommunicationCtrl.class);
    }
}
