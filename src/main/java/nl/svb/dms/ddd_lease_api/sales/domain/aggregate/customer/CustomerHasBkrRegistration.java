package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CustomerHasBkrRegistration(Boolean customerHasBkrRegistration) {

    public static CustomerHasBkrRegistration of(Boolean customerHasBkrRegistration) {
        return new CustomerHasBkrRegistration(customerHasBkrRegistration);
    }
}