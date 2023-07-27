package com.dicygroup.loyaltyprogram.models.plans.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointRulePlanTest {

    @Test
    void ruleShouldBeCreated() {
        PointRule pointRule = new PointRule(1.0);
        assertEquals(1.0, pointRule.getPointValueInCurrency());
    }

    @Test
    void ruleShouldNotBeCreatedWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new PointRule(-1.0));
    }

    @Test
    void ruleShouldNotBeCreatedWithZeroValue() {
        assertThrows(IllegalArgumentException.class, () -> new PointRule(0.0));
    }

    @Test
    void ruleShouldNotBeCreatedWithNullValue() {
        assertThrows(NullPointerException.class, () -> new PointRule(null));
    }

    @Test
    void ruleShouldBeApplied() {
        PointRule pointRule = new PointRule(1.0);
        assertEquals(1, pointRule.apply(1.0));
        pointRule = new PointRule(2.0);
        assertEquals(2, pointRule.apply(4.0));
        pointRule = new PointRule(0.5);
        assertEquals(2, pointRule.apply(1.0));
    }

    @Test
    void ruleShouldNotBeAppliedWithNegativeAmount() {
        PointRule pointRule = new PointRule(1.0);
        assertThrows(IllegalArgumentException.class, () -> pointRule.apply(-1.0));
    }

    @Test
    void ruleShouldNotBeAppliedWithNullAmount() {
        PointRule pointRule = new PointRule(1.0);
        assertThrows(NullPointerException.class, () -> pointRule.apply(null));
    }
}
