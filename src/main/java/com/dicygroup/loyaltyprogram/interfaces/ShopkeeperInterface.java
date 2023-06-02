package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.PlanManager;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shopkeepers/")
@Slf4j
@RequiredArgsConstructor
public class ShopkeeperInterface {

    private final PlanManager planManager;


    @PostMapping("plans")
    public Plan create(@RequestBody AbstractPlan plan) {
       return planManager.savePlan(plan);
    }
}
