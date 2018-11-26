package com.greencrane.utils.mail;

import com.greencrane.entity.Customer;
import com.greencrane.entity.File;


/**
 * Created by Marcin on 23.09.2018.
 */
public interface MailUtils {
    void sendCustomerData(Customer customer, File file);
    void sendEmailToCustomer(Customer customer);
}
