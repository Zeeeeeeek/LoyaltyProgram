package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRegistry extends CrudRepository<Customer, Long> {
}
