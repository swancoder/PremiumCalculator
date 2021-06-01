package com.insurance.domain.policy;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PolicyBuilder {
    Policy policy;


    public static PolicyBuilder getBuilder() {
        return new PolicyBuilder();
    }

    public PolicyBuilder createPolicy(String number) {
        this.policy = new Policy(number);
        return this;
    }

    public PolicyBuilder addPolicyObject(String number) {
        checkNotNull(policy);
        this.policy.addObject(new PolicyObject(number));
        return this;
    }

    public PolicyBuilder addSubObject(String number, double sumInsured, RiskType type) {
        checkNotNull(policy);
        List<PolicyObject> objects = policy.getObjects();
        PolicyObject obj = objects.get(objects.size() - 1);
        obj.addSubObject(new PolicySubObject(number, sumInsured, type));
        return this;
    }

    public Policy build() {
        return policy;
    }

}
