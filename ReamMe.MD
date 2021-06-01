This repository contains a solution for insurance policy premium calculation.

The solution got two packages:
* com.insurance.domain.calculate
* com.insurance.domain.policy

The first package contains an interface PremiumCalculator and it's implementation, which currently covers only two risks types such as "Fire" and "Theft".
Later, when another risks would be covered by insurance or calculation algorithm would be changed, we should be able to keep this implementation for historical reasons and add another implementation for current premium calculations.

Second package contains policy-related classes, which store most of the insurance information and provide a very basic API for collecting it.

NB. 
1. The author is aware of Java streams functionality, but for sake of speed and simplicity during testing/debugging simple iterators were used.
2. For calculation handling BigDecimal type was chosen, since using just simple 'double' type wasn't safe enough due to rounding peculiarities. Even after that the author has discovered that new BigDecimal(double) and BigDecimal.valueOf(double) may produce slightly different results.
