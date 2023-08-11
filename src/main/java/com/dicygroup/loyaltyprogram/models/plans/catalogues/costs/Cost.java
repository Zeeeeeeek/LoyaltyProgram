package com.dicygroup.loyaltyprogram.models.plans.catalogues.costs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LevelCost.class, name = "level"),
        @JsonSubTypes.Type(value = PointCost.class, name = "point")
})
public abstract class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("requiredPoints")
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
