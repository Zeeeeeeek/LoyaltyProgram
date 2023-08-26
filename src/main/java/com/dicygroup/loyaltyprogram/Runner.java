package com.dicygroup.loyaltyprogram;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.registries.CustomerRegistry;
import com.dicygroup.loyaltyprogram.registries.ShopkeeperRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

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
        populateShopkeepers();
        populateCustomers();
    }

    private void populateShopkeepers() {
        saveEntityIfIdDoesNotExist(
                shopkeeperRegistry,
                new Shopkeeper("pippo", "pluto"),
                1L);
        saveEntityIfIdDoesNotExist(
                shopkeeperRegistry,
                new Shopkeeper("paperino", "topolino"),
                2L);
    }

    private void populateCustomers() {
        saveEntityIfIdDoesNotExist(
                customerRegistry,
                new Customer("mario", "rossi", "1234567890"),
                1L);
    }

    private <T> void saveEntityIfIdDoesNotExist(CrudRepository<T, Long> repository, T entity, Long idToCheck) {
        repository.findById(idToCheck)
                .ifPresentOrElse(
                        existingEntity -> {
                        },
                        () -> repository.save(entity));
    }

}
