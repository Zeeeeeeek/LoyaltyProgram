package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.Cost;
import org.springframework.data.repository.CrudRepository;

public interface CostRepository extends CrudRepository<Cost, Long> {
}
