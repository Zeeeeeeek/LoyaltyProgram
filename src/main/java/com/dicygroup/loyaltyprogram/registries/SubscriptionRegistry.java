package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.subscription.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRegistry extends CrudRepository<Subscription, Long> {
}
