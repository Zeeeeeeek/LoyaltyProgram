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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private int pointValueInCurrency;

    @OneToOne(mappedBy = "pointRule")
    @Getter
    @JsonIgnore
    private AbstractPlan plan;

    public PointRule(int pointValueInCurrency) {
        this.pointValueInCurrency = requirePositiveValue(pointValueInCurrency);
    }

    public Integer apply(@NonNull Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }
        return (int) (amount / pointValueInCurrency);
    }

    private int requirePositiveValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }
        return value;
    }
}