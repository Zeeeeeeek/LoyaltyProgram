package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.LevelsPlan;
import com.dicygroup.loyaltyprogram.models.plans.PointsPlan;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Prize;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.LevelCost;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.PointCost;
import com.dicygroup.loyaltyprogram.registries.CatalogueRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogueManager {

    private final PlanManager planManager;
    private final CatalogueRegistry catalogueRegistry;

    public boolean createCatalogue(Long planId, Catalogue catalogue) {
        AbstractPlan plan = planManager.getPlanById(planId);
        catalogue.setPlan(plan);
        List<Prize> prizes = catalogue.getPrizes();
        if (prizesAreNotCompatibleWithPlanType(plan, prizes)) return false;
        try {
            safeSave(catalogue, planId);
            return true;
        } catch (Exception e) {
            log.error("Error while saving catalogue", e);
            return false;
        }
    }

    private void safeSave(Catalogue catalogue, Long planId) {
        if (catalogueRegistry.findByPlanId(planId) != null)
            catalogueRegistry.delete(catalogueRegistry.findByPlanId(planId));
        catalogueRegistry.save(catalogue);
    }

    private boolean prizesAreNotCompatibleWithPlanType(AbstractPlan plan, List<Prize> prizes) {
        if (plan instanceof PointsPlan) {
            return notAllMatch(prizes, prize -> prize.getCost() instanceof PointCost);
        }
        return notAllMatch(prizes.stream()
                .filter(prize -> prize.getCost() instanceof LevelCost)
                .map(prize -> (LevelCost) prize.getCost())
                .toList(),
                levelCost -> levelCost.getRequiredLevel() <= ((LevelsPlan) plan).getLevelNumber());
    }

    private <T> boolean notAllMatch(List<T> prizes, Predicate<T> predicate) {
        return !prizes.stream().allMatch(predicate);
    }

    public Catalogue getCatalogue(Long planId) {
        return catalogueRegistry.findByPlanId(planId);
    }
}
