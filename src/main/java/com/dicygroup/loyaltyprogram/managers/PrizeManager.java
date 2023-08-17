package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.Prize;
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

    public Boolean pickUpPrize(Long prizeId, Long planId, Long customerId) {
        int actualPoint = subscriptionManager.getCustomerStatus(customerId, planId);
        int prizeCost = getPrize(prizeId).getCost().getRequiredPoints();


        if (actualPoint >= prizeCost) {
            Integer newPoints = actualPoint - prizeCost;
            subscriptionManager.setPoints(customerId, planId, newPoints);
            return true;
        }

        return false;
    }

    public Prize getPrize(Long prizeId) {
        return prizeRegistry.findById(prizeId).orElseThrow();
    }
}
