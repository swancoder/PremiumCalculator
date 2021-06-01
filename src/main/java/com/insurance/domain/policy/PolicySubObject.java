package com.insurance.domain.policy;

import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;

public class PolicySubObject {

    private String name;
    @Getter(AccessLevel.PACKAGE)
    private BigDecimal sumInsured;
    @Getter(AccessLevel.PACKAGE)
    private RiskType type;

     PolicySubObject(String name, double sumInsured, RiskType type) {
        this.name = name;
        this.sumInsured = BigDecimal.valueOf(sumInsured);
        this.type = type;
    }
}
