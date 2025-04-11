package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.quote;

import java.util.UUID;

public record QuoteReference(UUID quoteReference) {

    public static QuoteReference of(UUID quoteReference) {
        return new QuoteReference(quoteReference);
    }
}