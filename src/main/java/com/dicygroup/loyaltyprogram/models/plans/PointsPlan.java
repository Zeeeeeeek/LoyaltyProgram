package com.dicygroup.loyaltyprogram.models.plans;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class PointsPlan extends AbstractPlan {

    public PointsPlan(Long id, String owner) {
        super(id, owner);
    }
}