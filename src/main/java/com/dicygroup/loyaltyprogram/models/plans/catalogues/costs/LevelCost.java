package com.dicygroup.loyaltyprogram.models.plans.catalogues.costs;

import lombok.Getter;

@Getter
public class LevelCost extends Cost {

    private int requiredLevel;

    public LevelCost(int requiredPoints) {
        super(requiredPoints);
    }

    public void setRequiredLevel(int requiredLevel) {
        requirePositiveValue(requiredLevel);
        this.requiredLevel = requiredLevel;
    }
}
