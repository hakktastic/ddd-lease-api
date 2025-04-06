package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CustomerYearlyIncome(Double customerYearlyIncome) {

    public static CustomerYearlyIncome of(Double customerYearlyIncome) {
        return new CustomerYearlyIncome(customerYearlyIncome);
    }
}