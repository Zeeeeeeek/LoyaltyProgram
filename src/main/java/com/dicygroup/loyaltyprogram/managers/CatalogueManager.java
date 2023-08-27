package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.LevelsPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.plans.PointsPlan;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Prize;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.LevelCost;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.PointCost;
import com.dicygroup.loyaltyprogram.registries.CatalogueRegistry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogueManager {

    private final CatalogueRegistry catalogueRegistry;
    private final PrizeManager prizeManager;

    public Catalogue createCatalogue(AbstractPlan plan, Catalogue catalogue) {
        catalogue.setPlan(plan);
        List<Prize> prizes = catalogue.getPrizes();
        if (prizesAreNotCompatibleWithPlanType(plan, prizes)) throw new IllegalArgumentException("Prizes are not compatible with plan type");
        return safeSave(catalogue, plan.getId());
    }

    private Catalogue safeSave(Catalogue catalogue, Long planId) {
        if (catalogueRegistry.findByPlanId(planId) != null)
            catalogueRegistry.delete(catalogueRegistry.findByPlanId(planId));
        Catalogue savedCatalogue = catalogueRegistry.save(catalogue);
        prizeManager.saveAll(savedCatalogue, catalogue.getPrizes());
        return savedCatalogue;
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

    @Transactional
    public void deleteCatalogueByPlanId(Long planId) {
        Catalogue catalogue = catalogueRegistry.findByPlanId(planId);
        if(catalogue == null) return;
        prizeManager.deleteAllByCatalogueId(catalogue.getId());
        //catalogueRegistry.deleteByPlanId(planId);
    }
}
