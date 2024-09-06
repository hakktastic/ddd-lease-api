package nl.svb.dms.ddd_lease_api.sales.domain.event;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainEventPublisher;

@RequiredArgsConstructor(staticName = "of")
public class SalesEventPublishVisitor implements SalesVisitor {

    private final QuoteDomainEventPublisher quoteDomainEventPublisher;

    @Override
    public void visit(QuoteFilledOutEvent quoteFilledOutEvent) {
        quoteDomainEventPublisher.publish(quoteFilledOutEvent);
    }

    @Override
    public void visit(InstallmentCalculatedEvent installmentCalculatedEvent) {
        quoteDomainEventPublisher.publish(installmentCalculatedEvent);
    }

    @Override
    public void visit(QuoteSignedEvent quoteSignedEvent) {
        quoteDomainEventPublisher.publish(quoteSignedEvent);
    }
}