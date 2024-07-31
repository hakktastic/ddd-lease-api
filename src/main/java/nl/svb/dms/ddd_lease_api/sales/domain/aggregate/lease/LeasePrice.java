package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease;

public record LeasePrice(Double leasePrice) {

    public static LeasePrice of(Double leasePrice) {
        return new LeasePrice(leasePrice);
    }
}
