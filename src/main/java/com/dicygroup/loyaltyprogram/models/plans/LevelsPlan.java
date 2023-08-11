package com.dicygroup.loyaltyprogram.models.plans;

import com.dicygroup.loyaltyprogram.models.plans.rules.PointRule;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class LevelsPlan extends AbstractPlan {

    private int levelNumber;
    private int pointsPerLevel;

    public LevelsPlan(PointRule pointRule, boolean isOpenToCoalition) {
        super(pointRule, isOpenToCoalition);
    }

    public int evalLevel(int points) {
        return points / pointsPerLevel;
    }
}