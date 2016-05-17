package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Address {

    @JsonIgnore
    @ManyToOne
    private Customer customer;

    @Id
    @GeneratedValue
    private Long id;

    Address() {}

    public Address(Customer customer, AddressType type, String street, int number, String district, String city) {
        this.customer = customer;
        this.setType(type);
        this.setStreet(street);
        this.setNumber(number);
        this.setDistrict(district);
        this.setCity(city);
    }

    private AddressType type;
    private String street;
    private int number;
    private String district;
    private String city;

    @Override
    public String toString() {
        return String.format("Address[id=%d, name='%s', cid=%s]", getId(), getStreet(), customer.toString());
    }

    public Long getId() {
        return id;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
