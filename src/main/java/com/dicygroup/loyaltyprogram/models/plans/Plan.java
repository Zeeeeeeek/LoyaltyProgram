package com.dicygroup.loyaltyprogram.models.plans;

import com.dicygroup.loyaltyprogram.models.plans.rules.PointRule;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.dicygroup.loyaltyprogram.models.catalog.Catalog;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PointsPlan.class, name = "points"),
        @JsonSubTypes.Type(value = LevelsPlan.class, name = "levels")
})
public interface Plan {

    Shopkeeper getOwner();

    Long getId();

    PointRule getPointRule();

    boolean isOpenToCoalition();

    void addCoalition(Shopkeeper shopkeeper);

    Catalog getCatalog();

    void setOpenToCoalition(boolean openToCoalition);
    
}