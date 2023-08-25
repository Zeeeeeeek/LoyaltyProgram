package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbstractPlanRegistry extends CrudRepository<AbstractPlan, Long> {

    @Query("SELECT p FROM AbstractPlan p WHERE p.owner = ?1")
    List<AbstractPlan> findByOwnerId(Shopkeeper shopKeeperId);

}