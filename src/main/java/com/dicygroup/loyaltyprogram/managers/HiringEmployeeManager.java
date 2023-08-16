package com.dicygroup.loyaltyprogram.managers;

import com.dicygroup.loyaltyprogram.models.employee.Employee;
import com.dicygroup.loyaltyprogram.models.employee.EmployeeDTO;
import com.dicygroup.loyaltyprogram.models.shopkeepers.Shopkeeper;
import com.dicygroup.loyaltyprogram.registries.EmployeeRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HiringEmployeeManager {

    private final EmployeeRegistry employeeRegistry;

    public Employee addEmployee(Shopkeeper shopKeeperFromId, EmployeeDTO employee) {
        return employeeRegistry.save(Employee.fromDTO(employee, shopKeeperFromId.getId()));
    }
}
