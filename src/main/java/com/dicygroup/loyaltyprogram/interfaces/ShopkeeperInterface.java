package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.CatalogueManager;
import com.dicygroup.loyaltyprogram.managers.HiringEmployeeManager;
import com.dicygroup.loyaltyprogram.managers.PlanManager;
import com.dicygroup.loyaltyprogram.managers.ShopkeeperManager;
import com.dicygroup.loyaltyprogram.models.employee.Employee;
import com.dicygroup.loyaltyprogram.models.employee.EmployeeDTO;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final CatalogueManager catalogueManager;

    private final HiringEmployeeManager hiringEmployeeManager;

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
        try {
            AbstractPlan plan = planManager.getPlanById(planId);
            plan.setCatalogue(catalogueManager.createCatalogue(planManager.getPlanById(planId), catalogue));
            planManager.savePlan(plan);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e) {
            log.error("Error while adding catalogue", e);
            return false;
        }
    }

    @GetMapping("{ownerId}/plans/{planId}/catalogue")
    public Catalogue getCatalogue(@PathVariable Long planId, @PathVariable Long ownerId) {
        return catalogueManager.getCatalogue(planId);
    }

    @PutMapping("{ownerId}/plans/{planId}")
    public Plan modify(@PathVariable Long planId, @RequestBody AbstractPlan plan, @PathVariable Long ownerId) {
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

    @PostMapping("{shopkeeperId}/employees")
    public Employee addEmployee(@RequestBody EmployeeDTO employee, @PathVariable Long shopkeeperId) {
        return hiringEmployeeManager.addEmployee(shopkeeperManager.getShopKeeperFromId(shopkeeperId), employee);
    }

    @DeleteMapping("{ownerId}/plans/{planId}")
    public AbstractPlan deletePlan(@PathVariable Long planId, @PathVariable Long ownerId) {
        return planManager.deletePlan(planId);
    }

}
