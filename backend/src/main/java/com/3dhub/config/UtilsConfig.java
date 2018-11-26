package com.greencrane.config;

import com.greencrane.utils.mail.MailUtils;
import com.greencrane.utils.mail.MailUtilsFormater;
import com.greencrane.utils.mail.MailUtilsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Marcin on 30.09.2018.
 */
@Configuration
public class UtilsConfig {

    /*mail*/
    @Autowired
    @Qualifier(value = "mailCustomerFormaterUtils")
    MailUtilsFormater mailUtilsFormater;

    @Bean(name = "mailCustomerUtils")
    public MailUtils mailCustomerUtils() {
        return new MailUtilsImpl(mailUtilsFormater);
    }

    @Autowired
    @Qualifier(value = "mailToCustomerFormaterUtils")
    MailUtilsFormater mailToCustomerUtilsFormater;

    @Bean(name = "mailToCustomerUtils")
    public MailUtils mailToCustomerUtils() {
        return new MailUtilsImpl(mailToCustomerUtilsFormater);
    }
}
