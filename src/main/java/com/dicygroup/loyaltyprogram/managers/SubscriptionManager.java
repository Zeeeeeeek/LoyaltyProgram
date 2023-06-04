package com.dicygroup.loyaltyprogram.managers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionManager {
    // TODO: Change with a collection of Subscriptions when "Subscription" Entity will be created
    private final List<Object> subscriptions;

    public boolean subscribeCustomerToPlan(Long customerId, Long planId) {
        return this.subscribeCustomer(customerId, planId);
    }

    // TODO: Change with a collection of Subscriptions when "Subscription" Entity will be created
    private boolean subscribeCustomer(Long customerId, Long planId) {
        return subscriptions.add(new Object());
    }
}
