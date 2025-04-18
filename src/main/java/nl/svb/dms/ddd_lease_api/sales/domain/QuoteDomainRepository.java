package nl.svb.dms.ddd_lease_api.sales.domain;

import java.util.Optional;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.event.InstallmentCalculatedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;

public interface QuoteDomainRepository {

  void handle(QuoteFilledOutEvent quoteFilledOutEvent) throws QuoteNotFoundException;

  void handle(InstallmentCalculatedEvent installmentCalculatedEvent);

  void handle(QuoteSignedEvent quoteSignedEvent);

  Optional<Quote> findQuoteBy(QuoteReference quoteReference);
}