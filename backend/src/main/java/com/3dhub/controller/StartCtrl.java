package com.greencrane.controller;

import com.greencrane.consts.LogType;
import com.greencrane.utils.LoggingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class StartCtrl {

    @Autowired
    LoggingUtils loggingUtils;

    @GetMapping(path = "/start")
    public ResponseEntity<Map<String, String>> start(HttpServletRequest request) {
        loggingUtils.logEvent(request, LogType.START);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
