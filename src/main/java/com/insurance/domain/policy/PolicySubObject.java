package com.insurance.domain.policy;

import lombok.Getter;

import java.math.BigDecimal;

public class PolicySubObject {

    private String name;
    @Getter
    private BigDecimal sumInsured;
    @Getter
    private RiskType type;

    public PolicySubObject(String name, double sumInsured, RiskType type) {
        this.name = name;
        this.sumInsured = BigDecimal.valueOf(sumInsured);
        this.type = type;
    }
}
