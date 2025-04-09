package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.quote;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public record QuoteReference(UUID quoteReference) {

    public static QuoteReference of(UUID quoteReference) {
        return new QuoteReference(quoteReference);
    }
}