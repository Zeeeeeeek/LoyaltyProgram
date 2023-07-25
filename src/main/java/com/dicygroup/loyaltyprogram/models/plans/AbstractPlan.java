package com.dicygroup.loyaltyprogram.models.plans;

import com.dicygroup.loyaltyprogram.models.plans.rules.PointRule;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ToString
@NoArgsConstructor
public abstract class AbstractPlan implements Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)//temp
    private Shopkeeper owner;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private PointRule pointRule;

    @Getter
    private boolean isOpenToCoalition;

    protected AbstractPlan(PointRule pointRule, boolean isOpenToCoalition) {
        this.isOpenToCoalition = isOpenToCoalition;
        this.pointRule = pointRule;
    }

    public void setIsOpenToCoalition(boolean isOpenToCoalition) {
        this.isOpenToCoalition = isOpenToCoalition;
    }
}