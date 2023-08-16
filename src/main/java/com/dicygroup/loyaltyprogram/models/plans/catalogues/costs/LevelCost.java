package com.dicygroup.loyaltyprogram.models.plans.catalogues.costs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
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
