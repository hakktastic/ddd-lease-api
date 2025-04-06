package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CustomerSignature(String customerSignature) {

    public static CustomerSignature of(String customerSignature) {
        return new CustomerSignature(customerSignature);
    }
}