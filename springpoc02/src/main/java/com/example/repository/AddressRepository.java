package com.example.repository;

import com.example.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
    Collection<Address> findByCustomerName(String username);
}
