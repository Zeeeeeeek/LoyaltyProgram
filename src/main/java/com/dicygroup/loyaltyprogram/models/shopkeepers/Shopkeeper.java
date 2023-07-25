package com.dicygroup.loyaltyprogram.models.shopkeepers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @Getter
    @Setter
    @OneToMany
    private List<AbstractPlan> coalitionPlans;

    public Shopkeeper(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}