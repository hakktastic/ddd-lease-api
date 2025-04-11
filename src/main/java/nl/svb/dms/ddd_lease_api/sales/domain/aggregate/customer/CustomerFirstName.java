package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer;

public record CustomerFirstName(String customerFirstName) {

    public static CustomerFirstName of(String customerFirstName) {
        return new CustomerFirstName(customerFirstName);
    }
}