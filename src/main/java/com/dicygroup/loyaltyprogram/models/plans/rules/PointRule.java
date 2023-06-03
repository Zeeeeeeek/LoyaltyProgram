package com.dicygroup.loyaltyprogram.models.plans.rules;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
public class PointRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    private Double pointValueInCurrency;

    @OneToOne(mappedBy = "pointRule")
    @Getter
    @JsonIgnore
    private AbstractPlan plan;

    public PointRule(@NonNull Double pointValueInCurrency) {
        requirePositiveValue(pointValueInCurrency);
    }

    public Integer apply(@NonNull Double amount) {
        return (int) (requirePositiveValue(amount) / pointValueInCurrency);
    }

    private Double requirePositiveValue(Double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }
        return value;
    }
}