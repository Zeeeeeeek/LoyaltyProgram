package com.dicygroup.loyaltyprogram.models.shopkeepers;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Shopkeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String surname;


    public Shopkeeper(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}