package com.greencrane.repository;

import com.greencrane.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Marcin on 23.09.2018.
 */
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer save(Customer customer);
}
