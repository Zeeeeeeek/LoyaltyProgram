package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.models.subscription.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRegistry extends CrudRepository<Subscription, Long> {

    @Query("SELECT p FROM Subscription p WHERE p.subscriber.id = ?1 AND p.plan.id = ?2")
    Subscription findByIds(Long customerId, Long planId);
}
