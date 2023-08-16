package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.Prize;
import org.springframework.data.repository.CrudRepository;

public interface PrizeRegistry extends CrudRepository<Prize, Long> {
}
