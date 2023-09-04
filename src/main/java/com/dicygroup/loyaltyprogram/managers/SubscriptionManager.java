package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.subscription.Subscription;
import com.dicygroup.loyaltyprogram.registries.AbstractPlanRegistry;
import com.dicygroup.loyaltyprogram.registries.CustomerRegistry;
import com.dicygroup.loyaltyprogram.registries.SubscriptionRegistry;
import jakarta.transaction.Transactional;
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

    private Subscription subscribeCustomer(Long planId, Long customerId) {
        Customer customer = customerRegistry
                .findById(customerId)
                .orElseThrow();
        AbstractPlan plan = abstractPlanRegistry
                .findById(planId)
                .orElseThrow();
        return subscriptionRegistry.save(new Subscription(customer, plan));
    }

    public Boolean setPoints(Long customerId, Long planId, Integer points) {
        Subscription subscription = getSubscription(customerId, planId);
        subscription.setPoints(points);
        try {
            subscriptionRegistry.save(subscription);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Subscription getSubscription(Long customerId, Long planId) {
        return subscriptionRegistry
                .findByIds(customerId, planId);
    }

    public Integer getCustomerStatus (Long customerId, Long planId) {
        return getSubscription(customerId, planId).getPoints();
    }

    @Transactional
    public void deleteSubscriptionsByPlanId(Long planId) {
        subscriptionRegistry.deleteByPlanId(planId);
    }

    public Boolean unsubscribeCustomerFromPlan(Long planId, Long customerId) {
        Subscription subscription = getSubscription(customerId, planId);
        try {
            subscriptionRegistry.delete(subscription);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Subscription> getSubscriptions(Long customerId) {
        return subscriptionRegistry.findByCustomerId(customerId);
    }
}