package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import org.springframework.data.repository.CrudRepository;

public interface ShopkeeperRegistry extends CrudRepository<Shopkeeper, Long> {
}
