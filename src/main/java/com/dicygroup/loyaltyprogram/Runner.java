package com.dicygroup.loyaltyprogram;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.registries.CustomerRegistry;
import com.dicygroup.loyaltyprogram.registries.ShopkeeperRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
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
        long shopkeepersCount = countIterable(shopkeeperRegistry.findAll());
        if(shopkeepersCount == 0) {
            Shopkeeper shopkeeper1 = new Shopkeeper("pippo", "pluto");
            shopkeeperRegistry.save(shopkeeper1);
            Shopkeeper shopkeeper2 = new Shopkeeper("paperino", "paperone");
            shopkeeperRegistry.save(shopkeeper2);
        } else if (shopkeepersCount == 1) {
            Shopkeeper shopkeeper2 = new Shopkeeper("paperino", "paperone");
            shopkeeperRegistry.save(shopkeeper2);
        }
    }

    private void populateCustomers() {
        Iterable<Customer> customers = customerRegistry.findAll();
        if (countIterable(customers) < 1) {
            Customer customer1 = new Customer("Dicy", "Customer", "073321934553");
            customerRegistry.save(customer1);
        }
    }

    private <T> long countIterable(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).count();
    }

}
