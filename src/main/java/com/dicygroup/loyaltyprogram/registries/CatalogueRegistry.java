package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.Catalogue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatalogueRegistry extends CrudRepository<Catalogue, Long> {

    @Query("SELECT c FROM Catalogue c WHERE c.plan.id = ?1")
    List<Catalogue> findByPlanId(Long planId);
}
