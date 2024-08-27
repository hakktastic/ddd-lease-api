package nl.svb.dms.ddd_lease_api.sales.application.rest;

import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteStatus;
import org.springframework.http.HttpStatusCode;

import java.net.URI;
import java.util.UUID;

public record QuoteResponse(HttpStatusCode httpStatusCode, UUID quoteReference, QuoteStatus quoteStatus, URI uri) {

    public static QuoteResponse of(HttpStatusCode httpStatusCode, UUID quoteReference, QuoteStatus quoteStatus, URI uri) {
        return new QuoteResponse(httpStatusCode, quoteReference, quoteStatus, uri);
    }
}
