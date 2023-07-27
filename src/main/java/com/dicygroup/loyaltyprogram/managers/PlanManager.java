package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.registries.AbstractPlanRegistry;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PlanManager {

    private final AbstractPlanRegistry abstractPlanRegistry;

    public Plan savePlan(AbstractPlan plan) {
        return abstractPlanRegistry.save(plan);
    }

    public Iterable<AbstractPlan> getPlanList() {
        return abstractPlanRegistry.findAll();
    }

    public List<AbstractPlan> getPlansOpenToCoalition() {
        return StreamSupport.stream(getPlanList().spliterator(), false)
                .filter(AbstractPlan::isOpenToCoalition)
                .toList();
    }

    public AbstractPlan getPlanById(Long planId) {
        return abstractPlanRegistry
                .findById(planId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Plan addCoalition(Long planId, Shopkeeper shopkeeper) {
        AbstractPlan plan = getPlanById(planId);
        if (plan.isOpenToCoalition())
            plan.addCoalition(shopkeeper);
        return savePlan(plan);
    }

    public Plan createAndSavePlan(AbstractPlan plan, Shopkeeper shopkeeper) {
        plan.setOwner(shopkeeper);
        return savePlan(plan);
    }
}