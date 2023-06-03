package com.dicygroup.loyaltyprogram.models.plans.rules;

import lombok.Getter;
import lombok.NonNull;

public record PointRule(@Getter @NonNull Double pointValueInCurrency) {
    public PointRule {
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