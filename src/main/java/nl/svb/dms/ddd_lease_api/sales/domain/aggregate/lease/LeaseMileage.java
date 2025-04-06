package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record LeaseMileage(int leaseMileage) {

    public static LeaseMileage of(int leaseMileage) {
        return new LeaseMileage(leaseMileage);
    }
}