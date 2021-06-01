package com.insurance.domain.policy;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class Policy {
    @Getter
    @Setter
    private String number;
    @Getter
    @Setter
    private PolicyStatus status;
    @Getter
    private List<PolicyObject> objects;

    public Policy(String number) {
        this.number = number;
        this.objects = new ArrayList<>();
    }

    public void addObject(PolicyObject policyObject) {
        checkNotNull(policyObject);
        this.objects.add(policyObject);
    }

    public BigDecimal getSumInsured(RiskType riskType) {
        BigDecimal sumInsured = BigDecimal.ZERO;
        for (PolicyObject obj : objects) {
            sumInsured = sumInsured.add(obj.getSumInsured(riskType));
        }
        return sumInsured;
    }

}
