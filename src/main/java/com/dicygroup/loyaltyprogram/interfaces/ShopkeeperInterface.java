package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.PlanManager;
import com.dicygroup.loyaltyprogram.managers.ShopkeeperManager;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopkeepers/")
@Slf4j
@RequiredArgsConstructor
public class ShopkeeperInterface {

    private final PlanManager planManager;
    private final ShopkeeperManager shopkeeperManager;

    @GetMapping("plans")
    public Iterable<AbstractPlan> list() {
        return planManager.getPlanList();
    }

    @PostMapping("{ownerId}/plans")
    public Plan create(@RequestBody AbstractPlan plan, @PathVariable Long ownerId) {
       return planManager.createAndSavePlan(plan, shopkeeperManager.getShopKeeperFromId(ownerId));
    }

    @GetMapping("open-to-coalition")
    public List<AbstractPlan> getPlansOpenToCoalition() {
        return planManager.getPlansOpenToCoalition();
    }

    @PostMapping("{ownerId}/coalitions")
    public Plan addCoalition(@RequestBody Long planId, @PathVariable Long ownerId) {
        return planManager.addCoalition(planId, shopkeeperManager.getShopKeeperFromId(ownerId));
    }

}
