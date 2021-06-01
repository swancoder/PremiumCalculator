package com.insurance.domain.calculate;

import com.insurance.domain.policy.Policy;
import com.insurance.domain.policy.PolicyObject;
import com.insurance.domain.policy.PolicySubObject;
import com.insurance.domain.policy.RiskType;
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
        assertEquals( 0, calc.calculate(new Policy("#123")));
    }

    @Test
    public void acceptanceTestOne() {
        PolicyObject policyObject = new PolicyObject("p-obj #123");
        policyObject.addSubObject(new PolicySubObject("s-obj #123/1", 100, RiskType.FIRE));
        policyObject.addSubObject(new PolicySubObject("s-obj #123/2", 8, RiskType.THEFT));
        Policy policy = new Policy("policy #123");
        policy.addObject(policyObject);

        assertEquals(2.28, calc.calculate(policy));
    }

    @Test
    public void acceptanceTestTwo() {
        PolicyObject policyObject = new PolicyObject("p-obj #123");
        policyObject.addSubObject(new PolicySubObject("s-obj #123/1", 100, RiskType.FIRE));
        policyObject.addSubObject(new PolicySubObject("s-obj #123/2", 100, RiskType.THEFT));
        Policy policy = new Policy("policy #123");
        policy.addObject(policyObject);

        policyObject = new PolicyObject("p-obj #124");
        policyObject.addSubObject(new PolicySubObject("s-obj #124/1", 400, RiskType.FIRE));
        policyObject.addSubObject(new PolicySubObject("s-obj #124/2", 2.51, RiskType.THEFT));
        policy.addObject(policyObject);


        assertEquals(17.13, calc.calculate(policy));
    }

}