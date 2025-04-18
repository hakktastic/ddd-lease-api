package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract;

import java.util.UUID;

public record ContractReference(UUID contractReference) {

  public static ContractReference of() {
    return new ContractReference(UUID.randomUUID());
  }

  public static ContractReference of(UUID quoteReference) {
    return new ContractReference(quoteReference);
  }
}