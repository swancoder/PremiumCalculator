package com.insurance.domain.policy;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@EqualsAndHashCode
public class PolicyObject {
    @Setter @Getter
    private String name;
    @Getter
    private List<PolicySubObject> subObjects;

    public PolicyObject(String name) {
        this.name = name;
        this.subObjects = new ArrayList<>();
    }

    public void addSubObject(PolicySubObject policySubObject) {
        checkNotNull(policySubObject);
        this.subObjects.add(policySubObject);
    }

    public BigDecimal getSumInsured(RiskType type) {
        BigDecimal result = BigDecimal.ZERO;
        for (PolicySubObject subObj: subObjects) {
            if (subObj.getType().equals(type)) {
                result = result.add(subObj.getSumInsured());
            }
        }
        return result;
    }
}
