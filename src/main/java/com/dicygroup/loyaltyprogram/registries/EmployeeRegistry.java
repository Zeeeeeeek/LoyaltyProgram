package com.dicygroup.loyaltyprogram.registries;

import com.dicygroup.loyaltyprogram.models.employee.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRegistry extends CrudRepository<Employee, Long> {
}
