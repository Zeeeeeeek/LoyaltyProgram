package com.dicygroup.loyaltyprogram.models.plans;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@ToString
@NoArgsConstructor
public abstract class AbstractPlan implements Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Shopkeeper owner;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private PointRule pointRule;

    private boolean isOpenToCoalition;

    @OneToMany @Fetch(FetchMode.JOIN)
    private List<Shopkeeper> coalition;

    @OneToOne
    private Catalogue catalogue;


    protected AbstractPlan(PointRule pointRule, boolean isOpenToCoalition) {
        this.isOpenToCoalition = isOpenToCoalition;
        this.pointRule = pointRule;
    }

    public void setIsOpenToCoalition(boolean isOpenToCoalition) {
        this.isOpenToCoalition = isOpenToCoalition;
    }

    @Override
    public void addCoalition(Shopkeeper shopkeeper) {
        if(shopkeeper.equals(owner) || coalition.contains(shopkeeper)) return;
        coalition.add(shopkeeper);
    }

    @Override
    public void setOpenToCoalition(boolean openToCoalition) {
        this.isOpenToCoalition = openToCoalition;
    }

    @Override
    public void setRule(PointRule rule) {
        this.pointRule = rule;
    }
}