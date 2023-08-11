package com.dicygroup.loyaltyprogram.models.plans.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointRulePlanTest {

    @Test
    void ruleShouldBeCreated() {
        PointRule pointRule = new PointRule(1);
        assertEquals(1.0, pointRule.getPointValueInCurrency());
    }

    @Test
    void ruleShouldNotBeCreatedWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new PointRule(-1));
    }

    @Test
    void ruleShouldNotBeCreatedWithZeroValue() {
        assertThrows(IllegalArgumentException.class, () -> new PointRule(0));
    }

    @Test
    void ruleShouldBeApplied() {
        PointRule pointRule = new PointRule(1);
        assertEquals(1, pointRule.apply(1.0));
        pointRule = new PointRule(2);
        assertEquals(2, pointRule.apply(4.0));
    }

    @Test
    void ruleShouldNotBeAppliedWithNegativeAmount() {
        PointRule pointRule = new PointRule(1);
        assertThrows(IllegalArgumentException.class, () -> pointRule.apply(-1.0));
    }

    @Test
    void ruleShouldNotBeAppliedWithNullAmount() {
        PointRule pointRule = new PointRule(1);
        assertThrows(NullPointerException.class, () -> pointRule.apply(null));
    }
}
