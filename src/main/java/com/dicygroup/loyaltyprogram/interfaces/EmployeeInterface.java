package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.CustomersManager;
import com.dicygroup.loyaltyprogram.managers.HiringEmployeeManager;
import com.dicygroup.loyaltyprogram.managers.PurchaseManager;
import com.dicygroup.loyaltyprogram.managers.ShopkeeperManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees/")
@Slf4j
@RequiredArgsConstructor
public class EmployeeInterface {

    private final HiringEmployeeManager hiringEmployeeManager;
    private final CustomersManager customersManager;
    private final PurchaseManager purchaseManager;

    @PostMapping("purchase")
    public Boolean pushCase(@RequestHeader Long customerId, @RequestHeader Long planId, @RequestHeader Double prize) {
        customersManager.getCustomerFromId(customerId);
        return purchaseManager.addPurchase(customerId, planId, prize);
    }

}
