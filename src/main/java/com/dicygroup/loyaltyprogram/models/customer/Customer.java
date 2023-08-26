package com.dicygroup.loyaltyprogram.models.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String telephoneNumber;

    public Customer(String name, String surname, String telephoneNumber) {
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
    }
}
