package com.dicygroup.loyaltyprogram.models.plans.catalogues;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.Cost;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Cost cost;

    public Prize(String name, Cost cost) {
        this.name = name;
        this.cost = cost;
    }
}
