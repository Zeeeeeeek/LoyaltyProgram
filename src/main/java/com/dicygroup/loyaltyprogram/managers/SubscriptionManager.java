package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.subscription.Subscription;
import com.dicygroup.loyaltyprogram.registries.AbstractPlanRegistry;
import com.dicygroup.loyaltyprogram.registries.CustomerRegistry;
import com.dicygroup.loyaltyprogram.registries.SubscriptionRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionManager {
    private final SubscriptionRegistry subscriptionRegistry;

    private final CustomerRegistry customerRegistry;

    private final AbstractPlanRegistry abstractPlanRegistry;

    public Subscription subscribeCustomerToPlan(Long planId, Long customerId) {
        return this.subscribeCustomer(planId, customerId);
    }

    // TODO: Change with a collection of Subscriptions when "Subscription" Entity will be created
    private Subscription subscribeCustomer(Long planId, Long customerId) {
        Customer customer = customerRegistry
                .findById(customerId)
                .orElseThrow();
        AbstractPlan plan = abstractPlanRegistry
                .findById(planId)
                .orElseThrow();

        return subscriptionRegistry.save(new Subscription(customer, plan));
    }

    public Boolean subtractPoints(Long customerId, Long planId, Long prizeId) {
        return true;
    }
}