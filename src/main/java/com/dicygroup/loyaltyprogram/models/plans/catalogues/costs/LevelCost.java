package com.dicygroup.loyaltyprogram.models.plans.catalogues.costs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LevelCost extends Cost {

    @JsonProperty("requiredLevel")
    private int requiredLevel;

    public LevelCost(int requiredPoints, int requiredLevel) {
        super(requiredPoints);
        setRequiredLevel(requiredLevel);
    }

    public void setRequiredLevel(int requiredLevel) {
        requirePositiveValue(requiredLevel);
        this.requiredLevel = requiredLevel;
    }
}
