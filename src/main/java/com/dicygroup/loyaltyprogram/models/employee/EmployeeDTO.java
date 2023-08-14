package com.dicygroup.loyaltyprogram.models.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private String name;
    private String surname;
    private String email;

    public EmployeeDTO(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
