package com.example;

import com.example.model.Address;
import com.example.model.AddressType;
import com.example.model.Customer;
import com.example.repository.AddressRepository;
import com.example.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(javafx.application.Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(CustomerRepository customerRepository, AddressRepository addressRepository) {
        return (args) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // save a couple of customers
            customerRepository.save(new Customer("Jack", "jack@email.com", sdf.parse("10/04/1972"), 44));
            customerRepository.save(new Customer("Chloe", "chloe@email.com", sdf.parse("09/03/1973"), 45));
            customerRepository.save(new Customer("Kim", "kim@email.com", sdf.parse("08/02/1974"), 46));
            customerRepository.save(new Customer("David", "david@email.com", sdf.parse("02/02/1975"), 47));
            customerRepository.save(new Customer("Michelle", "michele@email.com", sdf.parse("01/01/1976"), 48));

            // save a couple of addresses
            addressRepository.save(new Address(customerRepository.findOne(1L), AddressType.HOME, "street 1", 1, "district", "city"));
            addressRepository.save(new Address(customerRepository.findOne(1L), AddressType.WORK, "street 2", 2, "district", "city"));
            addressRepository.save(new Address(customerRepository.findOne(2L), AddressType.HOME, "street 3", 3, "district", "city"));
            addressRepository.save(new Address(customerRepository.findOne(2L), AddressType.WORK, "street 4", 4, "district", "city"));
            addressRepository.save(new Address(customerRepository.findOne(3L), AddressType.HOME, "street 5", 5, "district", "city"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : customerRepository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch all addresses
            log.info("Addresses found with findAll():");
            log.info("-------------------------------");
            for (Address customer : addressRepository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");
        };
    }
}



