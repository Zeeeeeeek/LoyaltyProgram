package com.dicygroup.loyaltyprogram.models.plans.catalogues.costs;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class PointCost extends Cost {
    public PointCost(int requiredPoints) {
        super(requiredPoints);
    }
}
