package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.registries.AbstractPlanRegistry;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.dicygroup.loyaltyprogram.models.catalog.Catalog;
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

    public Catalog getCatalog(Long planId) {
        return getPlanById(planId).getCatalog();

    public Plan modifyAndSavePlan(Long planId, AbstractPlan plan, Shopkeeper shopKeeperFromId) {
        AbstractPlan planToModify = getPlanById(planId);

        if (planToModify.getOwner() != shopKeeperFromId)
            throw new IllegalArgumentException("The plan you are trying to modify is not yours");

        modifyPlan(planToModify, plan);

        // TODO Questo metodo salva tutto?
        return savePlan(planToModify);
    }

    private Plan modifyPlan(AbstractPlan planToModify, AbstractPlan newDetailsPlan) {
        planToModify.setCoalition(newDetailsPlan.getCoalition());
        planToModify.setOwner(newDetailsPlan.getOwner());
        planToModify.setOpenToCoalition(newDetailsPlan.isOpenToCoalition());
        planToModify.setPointRule(newDetailsPlan.getPointRule());
        return planToModify;
    }

    public List<AbstractPlan> getOwnedPlans(Shopkeeper shopKeeperId) {
        return abstractPlanRegistry
                .findByOwnerId(shopKeeperId);
    }
}