package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote;

import java.util.UUID;

public record QuoteReference(UUID quoteReference) {

  public static QuoteReference of() {
    return new QuoteReference(UUID.randomUUID());
  }

  public static QuoteReference of(UUID quoteReference) {
    return new QuoteReference(quoteReference);
  }
}