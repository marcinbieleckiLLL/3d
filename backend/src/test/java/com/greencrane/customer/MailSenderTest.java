package com.greencrane.customer;

import com.greencrane.service.file.FileService;
import com.greencrane.service.file.ZipFileCreator;
import com.greencrane.utils.mail.MailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Marcin on 23.09.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSenderTest extends TestsHelper {

    @Autowired
    @Qualifier("mailToCustomerUtils")
    MailUtils mailToCustomerUtils;

    @Autowired
    @Qualifier("mailCustomerUtils")
    MailUtils mailCustomerUtils;

    @Autowired
    FileService fileService;
    @Autowired
    ZipFileCreator zipFileCreator;

    @Test
    public void sendMailTest() throws Exception {
       //mailToCustomerUtils.sendEmailToCustomer(createCustomer());
        mailCustomerUtils.sendCustomerData(createCustomer(), fileService.createFile(zipFileCreator, createCustomer()));
    }
}
