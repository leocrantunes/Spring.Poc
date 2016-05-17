package com.example.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {

    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses = new HashSet<>();

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private Date birthday;
    private int age;

    protected Customer() {}

    public Customer(String name, String email, Date birthday, int age) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, name='%s']", id, name);
    }

}