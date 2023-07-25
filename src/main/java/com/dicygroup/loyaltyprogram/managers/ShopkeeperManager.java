package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.registries.ShopkeeperRegistry;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopkeeperManager {

    private final ShopkeeperRegistry shopkeeperRegistry;

    public Shopkeeper getShopKeeperFromId(Long id) {
        return shopkeeperRegistry
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
