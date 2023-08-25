package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.Prize;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PrizeRegistry extends CrudRepository<Prize, Long> {
    @Modifying
    @Query("DELETE FROM Prize p WHERE p.catalogue.id = ?1")
    void deleteAllByCatalogueId(Long catalogueId);
}
