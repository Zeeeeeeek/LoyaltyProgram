package com.dicygroup.loyaltyprogram.models.plans.catalogues.costs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LevelCost.class, name = "level"),
        @JsonSubTypes.Type(value = PointCost.class, name = "point")
})
public abstract class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @JsonProperty("requiredPoints")
    @Getter
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
