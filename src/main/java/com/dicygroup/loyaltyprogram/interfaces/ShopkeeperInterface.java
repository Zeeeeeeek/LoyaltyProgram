package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.LevelsPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shopkeepers/")
@Slf4j
public class ShopkeeperInterface {
    @PostMapping("plans")
    public Plan create(@RequestBody Plan plan) {
        log.info(plan.toString());
        return plan;
    }
}
