package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.CustomersManager;
import com.dicygroup.loyaltyprogram.managers.HiringEmployeeManager;
import com.dicygroup.loyaltyprogram.managers.PurchaseManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees/")
@Slf4j
@RequiredArgsConstructor
public class EmployeeInterface {

    private final CustomersManager customersManager;
    private final PurchaseManager purchaseManager;

    @PostMapping("purchase/{customerId}/{planId}/{cost}")
    public Boolean purchase(@PathVariable Long customerId, @PathVariable Long planId, @PathVariable Double cost) {
        customersManager.getCustomerFromId(customerId);
        return purchaseManager.addPurchase(customerId, planId, cost);
    }

}
