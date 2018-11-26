package com.greencrane.service;

import com.greencrane.entity.Logs;
import com.greencrane.repository.LogsRepository;
import com.greencrane.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface LogsService {
    Logs save(Logs logs);
}

@Service
class LogsServiceImpl implements LogsService {

    @Autowired
    LogsRepository logsRepository;
    @Autowired
    DateUtils dateUtils;

    @Override
    public Logs save(Logs logs) {
        logs.setModDate(dateUtils.getCurrentDate());
        return logsRepository.save(logs);
    }
}