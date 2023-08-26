package com.dicygroup.loyaltyprogram.models.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    //    private Shopkeeper shopkeeper;
    private Long shopkeeperId;

    public Employee(String name, String surname, String email, Long shopkeeperId) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.shopkeeperId = shopkeeperId;
    }

    public static Employee fromDTO(EmployeeDTO employeeDTO, Long shopkeeperId) {
        return new Employee(employeeDTO.getName(), employeeDTO.getSurname(), employeeDTO.getEmail(), shopkeeperId);
    }
}
