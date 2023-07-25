package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.registries.AbstractPlanRegistry;
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
}