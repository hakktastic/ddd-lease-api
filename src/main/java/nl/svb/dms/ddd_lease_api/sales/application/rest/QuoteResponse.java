package nl.svb.dms.ddd_lease_api.sales.application.rest;

import java.net.URI;
import java.util.UUID;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteStatus;
import nl.svb.dms.ddd_lease_api.sales.domain.command.CommandResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

record QuoteResponse(HttpStatusCode httpStatusCode, UUID quoteReference, QuoteStatus quoteStatus,
                     URI uri) {

  static QuoteResponse of(HttpStatusCode httpStatusCode, UUID quoteReference,
      QuoteStatus quoteStatus, URI uri) {
    return new QuoteResponse(httpStatusCode, quoteReference, quoteStatus, uri);
  }

  static QuoteResponse from(CommandResult commandResult) {
    final var quoteStatus = commandResult.getStatus();
    final var quoteReferenceUUID = commandResult.getQuote().getQuoteReference().quoteReference();

    return QuoteResponse.of(getHttpStatusFrom(quoteStatus), quoteReferenceUUID, quoteStatus,
        createUri(quoteReferenceUUID));
  }

  private static HttpStatus getHttpStatusFrom(QuoteStatus quoteStatus) {

    switch (quoteStatus) {
      case FILLED_OUT -> {
        return HttpStatus.CREATED;
      }
      case CALCULATED, SIGNED -> {
        return HttpStatus.NO_CONTENT;
      }
      default -> {
        return HttpStatus.BAD_REQUEST;
      }
    }
  }

  private static URI createUri(UUID quoteReferenceUUID) {
    return URI.create("/api/v1/quotes/" + quoteReferenceUUID);
  }
}