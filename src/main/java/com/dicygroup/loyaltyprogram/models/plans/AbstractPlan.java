package com.dicygroup.loyaltyprogram.models.plans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class AbstractPlan implements Plan {
    @Getter
    private Long id;

    @Getter
    private String owner;
}