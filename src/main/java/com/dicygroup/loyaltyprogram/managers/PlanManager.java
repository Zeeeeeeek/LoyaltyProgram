package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.registries.AbstractPlanRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanManager {
    private final AbstractPlanRegistry abstractPlanRegistry;

    public Plan savePlan(AbstractPlan plan){
        return abstractPlanRegistry.save(plan);
    }

//    public List<Plan> getPlanList(){
//
//    }

}
