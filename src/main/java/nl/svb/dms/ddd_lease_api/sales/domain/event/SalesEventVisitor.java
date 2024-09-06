package nl.svb.dms.ddd_lease_api.sales.domain.event;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainEventPublisher;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;

@RequiredArgsConstructor(staticName = "of")
public class SalesEventVisitor implements SalesVisitor {

    private final QuoteDomainRepository quoteDomainRepository;
    private final QuoteDomainEventPublisher quoteDomainEventPublisher;

    @Override
    public void visit(QuoteFilledOutEvent quoteFilledOutEvent) throws QuoteNotFoundException {

        quoteDomainRepository.save(quoteFilledOutEvent);
        quoteDomainEventPublisher.publish(quoteFilledOutEvent);
    }

    @Override
    public void visit(InstallmentCalculatedEvent installmentCalculatedEvent) {

        quoteDomainRepository.save(installmentCalculatedEvent);
        quoteDomainEventPublisher.publish(installmentCalculatedEvent);
    }

    @Override
    public void visit(QuoteSignedEvent quoteSignedEvent) {

        quoteDomainRepository.save(quoteSignedEvent);
        quoteDomainEventPublisher.publish(quoteSignedEvent);
    }
}