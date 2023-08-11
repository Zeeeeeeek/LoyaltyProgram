package com.dicygroup.loyaltyprogram.models.plans.catalogues.costs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public abstract class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int requiredPoints;
    
    protected Cost(int requiredPoints) {
        setRequiredPoints(requiredPoints);
    }
    
    public void setRequiredPoints(int requiredPoints) {
        requirePositiveValue(requiredPoints);
        this.requiredPoints = requiredPoints;
    }

    protected static void requirePositiveValue(int requiredPoints) {
        if (requiredPoints < 0) {
            throw new IllegalArgumentException("Required points cannot be negative");
        }
    }

}
