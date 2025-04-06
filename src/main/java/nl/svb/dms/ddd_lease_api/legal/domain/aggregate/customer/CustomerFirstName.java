package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CustomerFirstName(String customerFirstName) {

    public static CustomerFirstName of(String customerFirstName) {
        return new CustomerFirstName(customerFirstName);
    }
}