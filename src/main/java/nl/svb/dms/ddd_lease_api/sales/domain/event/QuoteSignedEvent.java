package nl.svb.dms.ddd_lease_api.sales.domain.event;

import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;

public class QuoteSignedEvent extends SalesEvent {

  public QuoteSignedEvent(Quote quote) {
    super(quote);
  }

  @Override
  public void accept(QuoteDomainRepository quoteDomainRepository) throws QuoteNotFoundException {
    quoteDomainRepository.handle(this);
  }
}