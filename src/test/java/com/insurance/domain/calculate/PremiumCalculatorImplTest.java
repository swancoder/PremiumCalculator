package com.insurance.domain.calculate;

import com.insurance.domain.policy.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PremiumCalculatorImplTest {

    PremiumCalculatorImpl calc = new PremiumCalculatorImpl();

    @Test
    public void policyIsNull() {
        assertThrows(NullPointerException.class, () -> calc.calculate(null));
    }

    @Test
    public void policyHasNoObjects() {
        assertEquals( 0, calc.calculate(PolicyBuilder.getBuilder().createPolicy("#123").build()));
    }

    @Test
    public void acceptanceTestOne() {
        Policy policy = PolicyBuilder.getBuilder().createPolicy("policy #123")
                .addPolicyObject("p-obj #123")
                    .addSubObject("s-obj #123/1", 100, RiskType.FIRE)
                    .addSubObject("s-obj #123/2", 8, RiskType.THEFT).build();

        assertEquals(2.28, calc.calculate(policy));
    }

    @Test
    public void acceptanceTestTwo() {
        Policy policy = PolicyBuilder.getBuilder().createPolicy("policy #123")
                .addPolicyObject("p-obj #123")
                    .addSubObject("s-obj #123/1", 100, RiskType.FIRE)
                    .addSubObject("s-obj #123/2", 100, RiskType.THEFT)
                .addPolicyObject("p-obj #124")
                    .addSubObject("s-obj #124/1", 400, RiskType.FIRE)
                    .addSubObject("s-obj #124/2", 2.51, RiskType.THEFT)
                .build();

        assertEquals(17.13, calc.calculate(policy));
    }

    @Test
    public void acceptanceTestTwo_DifferentSplit() {
        Policy policy = PolicyBuilder.getBuilder().createPolicy("policy #123")
                .addPolicyObject("p-obj #123")
                    .addSubObject("s-obj #123/1", 100, RiskType.FIRE)
                    .addSubObject("s-obj #123/2", 25, RiskType.THEFT)
                    .addSubObject("s-obj #123/2", 25, RiskType.THEFT)
                .addPolicyObject("p-obj #124")
                    .addSubObject("s-obj #124/1", 350, RiskType.FIRE)
                    .addSubObject("s-obj #124/2", 2.51, RiskType.THEFT)
                .addPolicyObject("p-obj #125")
                    .addSubObject("s-obj #125/1", 50, RiskType.FIRE)
                    .addSubObject("s-obj #125/2", 50, RiskType.THEFT)
                .build();

        assertEquals(17.13, calc.calculate(policy));
    }

}