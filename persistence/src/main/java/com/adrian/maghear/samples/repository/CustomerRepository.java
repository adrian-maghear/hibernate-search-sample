package com.adrian.maghear.samples.repository;

import java.util.List;

import com.adrian.maghear.samples.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

}
