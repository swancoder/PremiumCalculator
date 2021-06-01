package com.insurance.domain.policy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PolicyObjectTest {


    private final PolicyObject policyObject = new PolicyObject("my obj #123");

    @Test
    public void addNull() {
        assertThrows(NullPointerException.class, () -> policyObject.addSubObject(null));
    }

    @Test
    public void addSubObject() {
        policyObject.addSubObject(new PolicySubObject("name of the sub", 100.0, RiskType.FIRE));
        assertEquals(1, policyObject.getSubObjects().size());
    }

    @Test
    public void emptySubObjects() {
        assertEquals(BigDecimal.ZERO, policyObject.getSumInsured(RiskType.FIRE));
        assertEquals(BigDecimal.ZERO, policyObject.getSumInsured(RiskType.THEFT));
    }

    @Test
    public void oneFireSubObject() {
        policyObject.addSubObject(new PolicySubObject("s-obj #123/1", 100, RiskType.FIRE));
        assertEquals(BigDecimal.valueOf(100.0), policyObject.getSumInsured(RiskType.FIRE));
        assertEquals(BigDecimal.ZERO, policyObject.getSumInsured(RiskType.THEFT));
    }

    @Test
    public void oneTheftSubObject() {
        policyObject.addSubObject(new PolicySubObject("s-obj #123/1", 100, RiskType.THEFT));
        assertEquals(BigDecimal.valueOf(100.0), policyObject.getSumInsured(RiskType.THEFT));
        assertEquals(BigDecimal.ZERO, policyObject.getSumInsured(RiskType.FIRE));
    }

    @Test
    public void twoFireSubObject() {
        policyObject.addSubObject(new PolicySubObject("s-obj #123/1", 100, RiskType.FIRE));
        policyObject.addSubObject(new PolicySubObject("s-obj #123/2", 150, RiskType.FIRE));
        Policy policy = new Policy("my policy #123");
        policy.addObject(policyObject);
        assertEquals(BigDecimal.valueOf(250.0), policy.getSumInsured(RiskType.FIRE));
    }

    @Test
    public void twoDiffSubObject() {
        policyObject.addSubObject(new PolicySubObject("s-obj #123/1", 100, RiskType.FIRE));
        policyObject.addSubObject(new PolicySubObject("s-obj #123/2", 150, RiskType.THEFT));
        Policy policy = new Policy("my policy #123");
        policy.addObject(policyObject);
        assertEquals(BigDecimal.valueOf(100.0), policy.getSumInsured(RiskType.FIRE));
        assertEquals(BigDecimal.valueOf(150.0), policy.getSumInsured(RiskType.THEFT));
    }


}
