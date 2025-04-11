package nl.svb.dms.ddd_lease_api.sales.domain;

import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.event.InstallmentCalculatedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;

import java.util.Optional;

public interface QuoteDomainRepository {

    void save(QuoteFilledOutEvent quoteFilledOutEvent) throws QuoteNotFoundException;

    void save(InstallmentCalculatedEvent installmentCalculatedEvent);

    void save(QuoteSignedEvent quoteSignedEvent);

    Optional<Quote> findQuoteBy(QuoteReference quoteReference);

    Boolean hasCustomerBkrRegistration(QuoteReference quoteReference);
}