package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.LevelsPlan;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Prize;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.Cost;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.LevelCost;
import com.dicygroup.loyaltyprogram.registries.PrizeRegistry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrizeManager {

    private final SubscriptionManager subscriptionManager;
    private final PrizeRegistry prizeRegistry;

    public Boolean pickUpPrize(Long prizeId, AbstractPlan plan, Long customerId) {
        int actualPoint = subscriptionManager.getCustomerStatus(customerId, plan.getId());
        Cost prizeCost = getPrize(prizeId).getCost();
        if (prizeCost instanceof LevelCost levelCost &&
                (plan instanceof LevelsPlan levelsPlan && (levelsPlan.evalLevel(actualPoint) < levelCost.getRequiredLevel()))) {
                return false;
        }
        if (actualPoint < prizeCost.getRequiredPoints())
            return false;
        return removePoints(actualPoint, prizeCost.getRequiredPoints(), customerId, plan.getId());
    }

    private Boolean removePoints(Integer actualPoint, Integer prizeCost, Long customerId, Long planId) {
        return subscriptionManager.setPoints(customerId, planId, actualPoint - prizeCost);
    }

    public Prize getPrize(Long prizeId) {
        return prizeRegistry.findById(prizeId).orElseThrow();
    }

    @Transactional
    public void deletePrizesByCatalogueId(Long id) {
        prizeRegistry.deleteAllByCatalogueId(id);
    }
}
