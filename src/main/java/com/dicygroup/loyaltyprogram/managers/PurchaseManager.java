package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.subscription.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseManager {

    private final PlanManager planManager;
    private final SubscriptionManager subscriptionManager;

    public Boolean addPurchase(Long customerId, Long planId, Double price) {
        Plan plan = planManager.getPlanById(planId);

        Integer point = plan.getPointRule().apply(price);
        return subscriptionManager.setPoints(customerId, planId, point);
    }

}
