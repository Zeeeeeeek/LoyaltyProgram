package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.Prize;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.Cost;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.LevelCost;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.PointCost;
import com.dicygroup.loyaltyprogram.models.subscription.Subscription;
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
        Cost prizeCost = getPrize(prizeId).getCost();


        if (prizeCost instanceof LevelCost){
            if(actualPoint >= ((LevelCost) prizeCost).getRequiredLevel()){
                if (actualPoint >= prizeCost.getRequiredPoints()) {
                    return removePoints(actualPoint, prizeCost.getRequiredPoints(), customerId, planId);

                }else throw new RuntimeException("Not enough points");

            }else throw new RuntimeException("Not enough points");

        } else if (prizeCost instanceof PointCost) {
            if(actualPoint >= prizeCost.getRequiredPoints()){
                return removePoints(actualPoint, prizeCost.getRequiredPoints(), customerId, planId);

            }else throw new RuntimeException("Not enough points");
        }

        return false;
    }

    private Boolean removePoints(Integer actualPoint, Integer prizeCost, Long customerId, Long planId){
        Integer newPoints = actualPoint - prizeCost;
        subscriptionManager.setPoints(customerId, planId, newPoints);
        return true;

    }

    public Prize getPrize(Long prizeId) {
        return prizeRegistry.findById(prizeId).orElseThrow();
    }
}
