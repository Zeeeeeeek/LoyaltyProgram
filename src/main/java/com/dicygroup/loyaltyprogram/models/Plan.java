package com.dicygroup.loyaltyprogram.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PointsPlan.class, name = "points"),
        @JsonSubTypes.Type(value = LevelsPlan.class, name = "levels")
})
public interface Plan {

    String getOwner();

    Long getId();
}