package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease;

public record LeaseDuration(Long leaseDuration) {

  public static LeaseDuration of(Long leaseDuration) {
    return new LeaseDuration(leaseDuration);
  }
}