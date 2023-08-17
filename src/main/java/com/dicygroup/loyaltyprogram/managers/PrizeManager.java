package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.LevelsPlan;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Prize;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.Cost;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.LevelCost;
import com.dicygroup.loyaltyprogram.registries.PrizeRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrizeManager {

    private final SubscriptionManager subscriptionManager;
    private final PrizeRegistry prizeRegistry;
    private final PlanManager planManager;

    public Boolean pickUpPrize(Long prizeId, Long planId, Long customerId) {
        int actualPoint = subscriptionManager.getCustomerStatus(customerId, planId);
        Cost prizeCost = getPrize(prizeId).getCost();
        if (prizeCost instanceof LevelCost levelCost) {
            AbstractPlan plan = planManager.getPlanById(planId);
            if (plan instanceof LevelsPlan levelsPlan && (levelsPlan.evalLevel(actualPoint) < levelCost.getRequiredLevel())) {
                return false;
            }
        }
        if (actualPoint < prizeCost.getRequiredPoints())
            return false;
        return removePoints(actualPoint, prizeCost.getRequiredPoints(), customerId, planId);
    }

    private Boolean removePoints(Integer actualPoint, Integer prizeCost, Long customerId, Long planId) {
        return subscriptionManager.setPoints(customerId, planId, actualPoint - prizeCost);
    }

    public Prize getPrize(Long prizeId) {
        return prizeRegistry.findById(prizeId).orElseThrow();
    }
}
