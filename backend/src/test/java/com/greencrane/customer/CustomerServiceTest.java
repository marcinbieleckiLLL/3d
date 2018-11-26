package com.greencrane.customer;

import com.greencrane.entity.Customer;
import com.greencrane.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Marcin on 24.09.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest extends TestsHelper {

    @Autowired
    CustomerService customerService;

    @Test
    public void customerServiceTest() throws Exception {
        Customer toSave = createCustomer();
        Customer saved = customerService.save(toSave);
        Assert.assertEquals(toSave.getAdditionalInfo(), saved.getAdditionalInfo());
        Assert.assertEquals(toSave.getEmail(), saved.getEmail());
        Assert.assertEquals(toSave.getMobPhone(), saved.getMobPhone());
        Assert.assertEquals(toSave.getModDate(), saved.getModDate());
        Assert.assertEquals(toSave.getName(), saved.getName());
        Assert.assertEquals(toSave.getSkills(), saved.getSkills());
    }
}
