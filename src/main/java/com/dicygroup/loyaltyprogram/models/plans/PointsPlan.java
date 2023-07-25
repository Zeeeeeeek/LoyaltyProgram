package com.dicygroup.loyaltyprogram.models.plans;

import com.dicygroup.loyaltyprogram.models.plans.rules.PointRule;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class PointsPlan extends AbstractPlan {

    public PointsPlan(PointRule pointRule, boolean isOpenToCoalition) {
        super(pointRule, isOpenToCoalition);
    }
}