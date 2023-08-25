package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface CatalogueRegistry extends CrudRepository<Catalogue, Long> {

    @Query("SELECT c FROM Catalogue c WHERE c.plan.id = ?1")
    Catalogue findByPlanId(Long planId);

    @Modifying
    @Query("DELETE FROM Catalogue c WHERE c.plan.id = ?1")
    void deleteByPlanId(Long planId);
}
