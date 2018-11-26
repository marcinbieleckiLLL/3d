package com.greencrane.service;

import com.greencrane.utils.DateUtils;
import com.greencrane.entity.Customer;
import com.greencrane.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Marcin on 23.09.2018.
 */
public interface CustomerService {
    Customer save(Customer customer);
}
@Service
class CustomerServiceImpl implements CustomerService {

    @Autowired
    DateUtils dateUtils;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        if (customer == null) throw new NullPointerException();
        customer.setModDate(dateUtils.getCurrentDate());
        return customerRepository.save(customer);
    }
}


