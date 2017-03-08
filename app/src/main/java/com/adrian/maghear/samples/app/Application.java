package com.adrian.maghear.samples.app;

import com.adrian.maghear.samples.model.Address;
import com.adrian.maghear.samples.model.Customer;
import com.adrian.maghear.samples.repository.CustomerRepository;
import com.adrian.maghear.samples.matchers.CustomerMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.adrian.maghear.samples.model")
@ComponentScan(basePackages = { "com.adrian.maghear.samples.matchers" })
@EnableJpaRepositories(basePackages = "com.adrian.maghear.samples.repository")
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository, CustomerMatcher customerMatcher) {
		return (args) -> {
			repository.save(new Customer("Ludwig", "van", "Beethoven"));
            repository.save(new Customer("Ludwig", "van", "Bethoven"));
            repository.save(new Customer("Ludwig", "von", "Beethoven"));
            repository.save(new Customer("Ludwig", "van", "Beetoven"));
            repository.save(new Customer("Ludwig", "van", "Bethoveen"));
            repository.save(new Customer("Ludwih", "van", "Beethoven"));
            repository.save(new Customer("ludwig", "van", "beethoven"));
            repository.save(new Customer("Ludwig", "Beethoven"));
            repository.save(new Customer("Ludwig", "-", "Beethoven"));

            repository.save(new Customer("Jürgen", "Möllemann"));
            repository.save(new Customer("Jurgen", "Moellemann"));
            repository.save(new Customer("Jurgen", "Meollemann"));
            repository.save(new Customer("Jürg", "Möllemann"));
            repository.save(new Customer("Juurgen", "Möllemann"));
            repository.save(new Customer("Jürgen", "Mollemann"));

            Address address1 = new Address("Pannierstrasse", "Berlin", 12);
            Address address2 = new Address("Pannierstr.", "Berlin", 12);
            Address address3 = new Address("Pannierstraße", "Berlin", 12);
            Address address4 = new Address("Pannierstrasse", "Munchen", 12);
            Address address5 = new Address("Pannierstrase", "Berlin", 15);
            Address address6 = new Address("Rohdestrasse", "Berlin", 12);

            repository.save(new Customer("Manfred", "von", "Richthofen", address1));
            repository.save(new Customer("Manfred", "von", "Richthofen", address2));
            repository.save(new Customer("Manfred", "von", "Richthofen", address3));
            repository.save(new Customer("Manfred", "von", "Richthofen", address4));
            repository.save(new Customer("Manfred", "von", "Ricthofen", address5));
            repository.save(new Customer("Manfred", "von", "Richthofen", address6));

            matchByFullName(customerMatcher, "Jurgen Möllemann");
            matchByFullName(customerMatcher, "Ludvig ban Beethoven");
            matchByFullName(customerMatcher, "Ludvig van Bethoven");

            matchByAll(customerMatcher, "Manfred", "Richthofen", "Berlin", "Pannierstrase");

		};
	}

    private void matchByFullName(CustomerMatcher customerMatcher, String fullName) {
        log.info("Customers matched by full name: " + fullName);
        log.info("-------------------------------");
        log.info("-------------------------------");
        log.info("-------------------------------");
        for (Customer customer : customerMatcher.findByFullName(fullName)) {
            log.info(customer.toString());
        }
        log.info("-------------------------------");
        log.info("-------------------------------");
        log.info("-------------------------------");
    }

    private void matchByAll(CustomerMatcher customerMatcher, String firstName, String lastName, String city, String street) {
        log.info("Customers matched by all: " + firstName + " " + lastName + " " + city + " " + street);
        log.info("-------------------------------");
        log.info("-------------------------------");
        log.info("-------------------------------");
        for (Customer customer : customerMatcher.findByFirstNameLastNameCityAndStreet(firstName, lastName, city, street)) {
            log.info(customer.toString());
        }
        log.info("-------------------------------");
        log.info("-------------------------------");
        log.info("-------------------------------");
    }

}
