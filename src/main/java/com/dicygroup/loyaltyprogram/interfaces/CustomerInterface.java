package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.CatalogueManager;
import com.dicygroup.loyaltyprogram.managers.PlanManager;
import com.dicygroup.loyaltyprogram.managers.PrizeManager;
import com.dicygroup.loyaltyprogram.managers.SubscriptionManager;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
import com.dicygroup.loyaltyprogram.models.subscription.Subscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers/")
@RequiredArgsConstructor
@Slf4j
public class CustomerInterface {

    private final PlanManager planManager;
    private final SubscriptionManager subscriptionManager;
    private final PrizeManager prizeManager;
    private final CatalogueManager catalogueManager;

    @GetMapping("plans")
    public Iterable<AbstractPlan> list() {
        return planManager.getPlanList();
    }

    @PostMapping("{customerId}/plans/{planId}")
    public Subscription subscribePlan(@PathVariable Long customerId, @PathVariable Long planId) {
        return subscriptionManager.subscribeCustomerToPlan(planId, customerId);
    }

    @PostMapping("{customerId}/plans/{planId}/catalog")
    public Catalogue getCatalog(@PathVariable Long planId, @PathVariable String customerId) {
        return catalogueManager.getCatalogue(planId);
    }

    @PostMapping("{customerId}/plans/{planId}/prizes/{prizeId}")
    public Boolean subtractPoints(@PathVariable Long customerId, @PathVariable Long planId, @PathVariable Long prizeId) {
        return prizeManager.pickUpPrize(prizeId, planId, customerId);
    }

    @GetMapping("{customerId}/status/{planId}")
    public Integer getCustomerStatus(@PathVariable Long customerId, @PathVariable Long planId) {
        return subscriptionManager.getCustomerStatus(customerId, planId);
    }
}
