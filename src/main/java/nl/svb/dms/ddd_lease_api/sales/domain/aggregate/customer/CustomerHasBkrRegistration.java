package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer;

public record CustomerHasBkrRegistration(Boolean customerHasBkrRegistration) {

    public static CustomerHasBkrRegistration of(Boolean customerHasBkrRegistration) {
        return new CustomerHasBkrRegistration(customerHasBkrRegistration);
    }
}