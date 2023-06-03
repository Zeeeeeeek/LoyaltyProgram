package com.dicygroup.loyaltyprogram.models.plans;

import com.dicygroup.loyaltyprogram.models.plans.rules.PointRule;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class AbstractPlan implements Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    @OneToOne
    private Shopkeeper owner;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    private PointRule pointRule;

    protected AbstractPlan(PointRule pointRule) {
        this.pointRule = pointRule;
    }
}