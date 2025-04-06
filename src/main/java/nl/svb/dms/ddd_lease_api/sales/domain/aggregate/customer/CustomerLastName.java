package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CustomerLastName(String customerLastName) {

    public static CustomerLastName of(String customerLastName) {
        return new CustomerLastName(customerLastName);
    }
}