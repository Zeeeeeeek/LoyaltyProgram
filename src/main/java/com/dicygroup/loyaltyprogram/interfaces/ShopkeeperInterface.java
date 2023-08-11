package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.CatalogueManager;
import com.dicygroup.loyaltyprogram.managers.PlanManager;
import com.dicygroup.loyaltyprogram.managers.ShopkeeperManager;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopkeepers/")
@Slf4j
@RequiredArgsConstructor
public class ShopkeeperInterface {

    private final PlanManager planManager;
    private final ShopkeeperManager shopkeeperManager;
    private final CatalogueManager catalogueManager;

    @GetMapping("plans")
    public Iterable<AbstractPlan> list() {
        return planManager.getPlanList();
    }

    @PostMapping("{ownerId}/plans")
    public Plan create(@RequestBody AbstractPlan plan, @PathVariable Long ownerId) {
       return planManager.createAndSavePlan(plan, shopkeeperManager.getShopKeeperFromId(ownerId));
    }

    @GetMapping("{ownerId}/owned-plans")
    public ResponseEntity<List<AbstractPlan>> getOwnedPlans(@PathVariable Long ownerId) {
        try {
            return ResponseEntity.ok(planManager.getOwnedPlans(shopkeeperManager.getShopKeeperFromId(ownerId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error while getting owned plans", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("{ownerId}/plans/{planId}/catalogue")
    public boolean addCatalogue(@PathVariable Long planId, @PathVariable Long ownerId, @RequestBody Catalogue catalogue) {
        return catalogueManager.createCatalogue(planId, catalogue);
    }

    @PutMapping("{ownerId}/plans/{planId}")
    public Plan modify(@PathVariable Long planId, @RequestBody AbstractPlan plan, @PathVariable Long ownerId) {
        // TODO Nel diagramma di sequenza la chiamata la fa con l'ID dell'esercente e non con l'esercente direttamente
        return planManager.modifyAndSavePlan(planId, plan, shopkeeperManager.getShopKeeperFromId(ownerId));
    }

    @GetMapping("open-to-coalition")
    public List<AbstractPlan> getPlansOpenToCoalition() {
        return planManager.getPlansOpenToCoalition();
    }

    @PostMapping("{shopkeeperId}/coalitions")
    public Plan addCoalition(@RequestBody Long planId, @PathVariable Long shopkeeperId) {
        return planManager.addCoalition(planId, shopkeeperManager.getShopKeeperFromId(shopkeeperId));
    }

}
