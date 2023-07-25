package com.dicygroup.loyaltyprogram;

import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.registries.ShopkeeperRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Runner implements CommandLineRunner {

    private final ShopkeeperRegistry shopkeeperRegistry;
    @Override
    public void run(String... args) {
        shopkeeperRegistry.save(
                new Shopkeeper("pippo", "pluto")
        );
        shopkeeperRegistry.save(
                new Shopkeeper("paperino", "paperone")
        );
        log.info("Created " + shopkeeperRegistry.count() + " shopkeepers");

        shopkeeperRegistry.findAll().forEach(shopkeeper -> log.info(shopkeeper.getName()));
    }
}
