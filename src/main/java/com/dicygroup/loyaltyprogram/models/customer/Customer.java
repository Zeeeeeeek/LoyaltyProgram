package com.dicygroup.loyaltyprogram.models.customer;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String surname;

    // TODO: Probably need to add other fields


    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
