package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.PlanManager;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers/")
@RequiredArgsConstructor
public class CustomerInterface {
    private final PlanManager planManager;

    @GetMapping("plans")
    public Iterable<AbstractPlan> list() {
        return planManager.getPlanList();
    }

    // TODO: This method should get the customer Id from the one logged in
    @PostMapping("{customerId}/plans/{planId}")
    public boolean subscribePlan(@PathVariable Long customerId, @PathVariable Long planId) {
        // Implement this
        return true;
    }
}
