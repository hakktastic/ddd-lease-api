package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer;

public record CustomerYearlyIncome(Double customerYearlyIncome) {

    public static CustomerYearlyIncome of(Double customerYearlyIncome) {
        return new CustomerYearlyIncome(customerYearlyIncome);
    }
}