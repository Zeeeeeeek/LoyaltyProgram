package com.dicygroup.loyaltyprogram;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.registries.CustomerRegistry;
import com.dicygroup.loyaltyprogram.registries.ShopkeeperRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
/**
 * This class is used to populate the database with some default entities
 */
public class Runner implements CommandLineRunner {

    private final ShopkeeperRegistry shopkeeperRegistry;
    private final CustomerRegistry customerRegistry;

    @Override
    public void run(String... args) {
        shopkeeperRegistry.save(
                new Shopkeeper("pippo", "pluto")
        );
        shopkeeperRegistry.save(
                new Shopkeeper("paperino", "paperone")
        );
        customerRegistry.save(
                new Customer("pippo", "pluto", "073321934553")
        );

    }
}
