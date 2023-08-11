package com.dicygroup.loyaltyprogram.managers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrizeManager {
    SubscriptionManager subscriptionManager;
    public Boolean getPrize(Long prizeId, Long planId, Long customerId) {
        return subscriptionManager.subtractPoints(customerId, planId, prizeId);
    }
}
