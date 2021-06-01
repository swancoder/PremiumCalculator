package com.insurance.domain.calculate;

import com.insurance.domain.policy.Policy;
import com.insurance.domain.policy.RiskType;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkNotNull;

public class PremiumCalculatorImpl implements PremiumCalculator {
    public static final double FIRE_THRESHOLD = 100.0;
    public static final double THEFT_THRESHOLD = 15.0;
    public static final double FIRE_DEFAULT_COEFF = 0.014;
    public static final double FIRE_ABOVE_THRESHOLD_COEFF = 0.024;
    public static final double THEFT_DEFAULT_COEFF = 0.11;
    public static final double THEFT_ABOVE_THRESHOLD_COEFF = 0.05;

    @Override
    public Double calculate(Policy policy) {
        checkNotNull(policy);
        return calculatePremium(policy);
    }

    public double calculatePremium(Policy policy) {
        BigDecimal fireSumInsured = policy.getSumInsured(RiskType.FIRE);
        double fireCoeff = fireSumInsured.doubleValue() > FIRE_THRESHOLD ?
                        FIRE_ABOVE_THRESHOLD_COEFF : FIRE_DEFAULT_COEFF;
        BigDecimal theftSumInsured = policy.getSumInsured(RiskType.THEFT);
        double theftCoeff = theftSumInsured.doubleValue() >= THEFT_THRESHOLD ?
                        THEFT_ABOVE_THRESHOLD_COEFF : THEFT_DEFAULT_COEFF;
        BigDecimal firePremium = fireSumInsured.multiply(BigDecimal.valueOf(fireCoeff));
        BigDecimal theftPremium = theftSumInsured.multiply(BigDecimal.valueOf(theftCoeff));
        return firePremium.add(theftPremium).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }


}
