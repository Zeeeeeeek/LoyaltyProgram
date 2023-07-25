package com.dicygroup.loyaltyprogram.models.plans;

import com.dicygroup.loyaltyprogram.models.plans.rules.PointRule;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class LevelsPlan extends AbstractPlan {

    public LevelsPlan(PointRule pointRule, boolean isOpenToCoalition) {
        super(pointRule, isOpenToCoalition);
    }
}