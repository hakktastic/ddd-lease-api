package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record LeasePrice(Double leasePrice) {

    public static LeasePrice of(Double leasePrice) {
        return new LeasePrice(leasePrice);
    }
}