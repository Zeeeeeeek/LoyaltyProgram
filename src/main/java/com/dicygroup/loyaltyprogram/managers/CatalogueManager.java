package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
import com.dicygroup.loyaltyprogram.registries.CatalogueRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogueManager {

    private final PlanManager planManager;
    private final CatalogueRegistry catalogueRegistry;

    public boolean createCatalogue(Long planId, Catalogue catalogue) {
        AbstractPlan plan = planManager.getPlanById(planId);
        catalogue.setPlan(plan);
        try {
            if(catalogueRegistry.findByPlanId(planId) != null)
                catalogueRegistry.delete(catalogueRegistry.findByPlanId(planId));
            catalogueRegistry.save(catalogue);
            return true;
        } catch (Exception e) {
            log.error("Error while saving catalogue", e);
            return false;
        }
    }

    public Catalogue getCatalogue(Long planId) {
        return catalogueRegistry.findByPlanId(planId);
    }
}
