package com.dicygroup.loyaltyprogram.models.plans;


import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class LevelsPlan extends AbstractPlan {

    public LevelsPlan(Long id, String owner) {
        super(id, owner);
    }
}