package nl.svb.dms.ddd_lease_api.sales.domain;

import nl.svb.dms.ddd_lease_api.sales.domain.event.InstallmentCalculatedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;
import org.jmolecules.architecture.hexagonal.Port;

@Port
public interface QuoteDomainEventPublisher {

    void publish(QuoteFilledOutEvent quoteFilledOutEvent);

    void publish(InstallmentCalculatedEvent installmentCalculatedEvent);

    void publish(QuoteSignedEvent quoteSignedEvent);
}