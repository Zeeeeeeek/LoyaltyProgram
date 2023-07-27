package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import org.springframework.data.repository.CrudRepository;

public interface AbstractPlanRegistry extends CrudRepository<AbstractPlan, Long> {
}