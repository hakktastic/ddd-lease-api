package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer;

public record CustomerLastName(String customerLastName) {

  public static CustomerLastName of(String customerLastName) {
    return new CustomerLastName(customerLastName);
  }
}