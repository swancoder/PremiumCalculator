package com.insurance.domain.calculate;

import com.insurance.domain.policy.Policy;

public interface PremiumCalculator {
    Double calculate(Policy policy);
}
