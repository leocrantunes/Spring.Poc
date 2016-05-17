package com.example.repository;

import com.example.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
}
