package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease;

public record LeaseMileage(int leaseMileage) {

  public static LeaseMileage of(int leaseMileage) {
    return new LeaseMileage(leaseMileage);
  }
}