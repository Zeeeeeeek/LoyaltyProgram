package com.dicygroup.loyaltyprogram.models.plans;

import lombok.Getter;

public class PointsPlan implements Plan {

    @Getter
    private Long id;

    @Getter
    private String owner;
}