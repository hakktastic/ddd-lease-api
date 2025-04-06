package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CustomerEmail(String customerEmail) {

    public static CustomerEmail of(String customerEmail) {
        return new CustomerEmail(customerEmail);
    }
}