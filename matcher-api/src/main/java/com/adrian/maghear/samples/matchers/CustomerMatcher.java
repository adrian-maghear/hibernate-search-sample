package com.adrian.maghear.samples.matchers;

import com.adrian.maghear.samples.model.Customer;

import java.util.List;

public interface CustomerMatcher {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFullName(String lastName);

    List<Customer> findByFirstNameLastNameCityAndStreet(String firstName, String lastName, String city, String street);

}
