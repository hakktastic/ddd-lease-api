package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record LeaseDuration(Long leaseDuration) {

    public static LeaseDuration of(Long leaseDuration) {
        return new LeaseDuration(leaseDuration);
    }
}