package com.example.controller;

import com.example.exception.UserNotFoundException;
import com.example.model.Address;
import com.example.repository.AddressRepository;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping("/{customerId}/addresses")
class AddressRestController {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String customerId, @RequestBody Address input) {
        this.validateCustomer(customerId);
        return this.customerRepository
                .findByName(customerId)
                .map(customer -> {
                    Address result = addressRepository.save(new Address(customer,
                            input.getType(), input.getStreet(), input.getNumber(),
                            input.getDistrict(), input.getCity()));

                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setLocation(ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(result.getId()).toUri());
                    return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
                }).get();
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    Address readAddress(@PathVariable String customerId, @PathVariable Long addressId) {
        this.validateCustomer(customerId);
        return this.addressRepository.findOne(addressId);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Address> readAddresses(@PathVariable String customerId) {
        this.validateCustomer(customerId);
        return this.addressRepository.findByCustomerName(customerId);
    }

    @Autowired
    AddressRestController(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    private void validateCustomer(String customerId) {
        this.customerRepository.findByName(customerId).orElseThrow(
                () -> new UserNotFoundException(customerId));
    }
}