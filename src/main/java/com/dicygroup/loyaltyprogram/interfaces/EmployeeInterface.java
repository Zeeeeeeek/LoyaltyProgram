package com.dicygroup.loyaltyprogram.interfaces;

import com.dicygroup.loyaltyprogram.managers.HiringEmployeeManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees/")
@Slf4j
@RequiredArgsConstructor
public class EmployeeInterface {

    private final HiringEmployeeManager hiringEmployeeManager;

}
