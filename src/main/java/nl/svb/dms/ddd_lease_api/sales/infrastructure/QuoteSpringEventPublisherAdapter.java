package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.SpringQuoteSignedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainEventPublisher;
import nl.svb.dms.ddd_lease_api.sales.domain.event.InstallmentCalculatedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.SalesEvent;
import org.jmolecules.architecture.hexagonal.Adapter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Adapter
class QuoteSpringEventPublisherAdapter implements QuoteDomainEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(QuoteFilledOutEvent quoteFilledOutEvent) {
        logPublishEvent(quoteFilledOutEvent);
        applicationEventPublisher.publishEvent(quoteFilledOutEvent);
    }

    @Override
    public void publish(InstallmentCalculatedEvent installmentCalculatedEvent) {
        logPublishEvent(installmentCalculatedEvent);
        applicationEventPublisher.publishEvent(installmentCalculatedEvent);
    }

    @Override
    public void publish(QuoteSignedEvent quoteSignedEvent) {

        logPublishEvent(quoteSignedEvent);
        applicationEventPublisher.publishEvent(SpringQuoteSignedEvent.from(quoteSignedEvent));
    }

    private void logPublishEvent(SalesEvent event) {
        log.debug("publishing event: {}", event);
    }
}