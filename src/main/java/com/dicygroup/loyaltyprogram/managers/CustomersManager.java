package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.registries.CustomerRegistry;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersManager {

    private final CustomerRegistry customerRegistry;

    public Customer getCustomerFromId(Long id) {
        return customerRegistry
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
