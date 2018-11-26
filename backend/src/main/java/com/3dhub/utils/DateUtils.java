package com.greencrane.utils;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Marcin on 23.09.2018.
 */
public interface DateUtils {
    Date getCurrentDate();
}

@Service
class DateUtilsImpl implements DateUtils {
    private Calendar cal;

    @PostConstruct
    public void initialize() {
        this.cal = Calendar.getInstance();
    }

    @Override
    public Date getCurrentDate() {
        return new Date(cal.getTimeInMillis());
    }
}
