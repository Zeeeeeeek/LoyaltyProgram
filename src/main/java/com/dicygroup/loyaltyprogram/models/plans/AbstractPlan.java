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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Shopkeeper> coalition;

    protected AbstractPlan(PointRule pointRule, boolean isOpenToCoalition) {
        this.isOpenToCoalition = isOpenToCoalition;
        this.pointRule = pointRule;
    }

    public void setIsOpenToCoalition(boolean isOpenToCoalition) {
        this.isOpenToCoalition = isOpenToCoalition;
    }

    @Override
    public void addCoalition(Shopkeeper shopkeeper) {
        coalition.add(shopkeeper);
    }

    @Override
    public void setOpenToCoalition(boolean openToCoalition) {
        this.isOpenToCoalition = openToCoalition;
    }
}