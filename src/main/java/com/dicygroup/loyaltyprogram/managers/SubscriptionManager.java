package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionManager {
    private final PlanManager planManager;

    // TODO: Change with a collection of Subscriptions when "Subscription<Customer, Plan" Entity will be created
    private final List<Pair<Customer, Long>> subscriptions;

    public Customer subscribeCustomerToPlan(Customer customer, Long planId) {
        return this.subscribeCustomer(customer, planId);
    }

    // TODO: Change with a collection of Subscriptions when "Subscription" Entity will be created
    private Customer subscribeCustomer(Customer customer, Long planId) {
        return subscriptions.add(new Pair(customer, planId)) ? customer : null;
    }

    public Boolean subtractPoints(Long customerId, Long planId, Long prizeId) {
        return true;
    }
}

// TODO: Local class to represent a subscription, soon to be changed into a standalone entity
class Pair<L, R> {
    private L l;
    private R r;
    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }
    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
}