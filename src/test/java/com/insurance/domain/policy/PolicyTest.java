package com.insurance.domain.policy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PolicyTest {


    private final Policy policy = new Policy("my policy #123");

    @Test
    public void addNullObject() {
        assertThrows(NullPointerException.class, () -> policy.addObject(null));
    }

    @Test
    public void addPolicyObject() {
        PolicyObject policyObject = new PolicyObject("p-obj #123");
        policy.addObject(policyObject);
        assertEquals(1, policy.getObjects().size());
        assertTrue(policy.getObjects().contains(policyObject));
    }

    @Test
    public void emptyPolicy() {
        assertEquals(BigDecimal.ZERO, policy.getSumInsured(RiskType.FIRE));
    }

    @Test
    public void oneFireSubObject() {
        PolicyObject policyObject = new PolicyObject("p-obj #123");
        policyObject.addSubObject(new PolicySubObject("s-obj #123/1", 100, RiskType.FIRE));
        policy.addObject(policyObject);
        assertEquals(BigDecimal.valueOf(100.0), policy.getSumInsured(RiskType.FIRE));
    }
}
