package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.SpringCreditRatingCheckedEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainEventPublisher;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.LegalEvent;
import org.jmolecules.architecture.hexagonal.Adapter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Adapter
public class ContractSpringEventPublisherAdapter implements ContractDomainEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(ContractFilledOutEvent contractFilledOutEvent) {
        logPublishEvent(contractFilledOutEvent);
        applicationEventPublisher.publishEvent(contractFilledOutEvent);
    }

    @Override
    public void publish(CreditRatingCheckedEvent creditRatingCheckedEvent) {
        logPublishEvent(creditRatingCheckedEvent);
        applicationEventPublisher.publishEvent(SpringCreditRatingCheckedEvent.from(creditRatingCheckedEvent));
    }

    private void logPublishEvent(LegalEvent event) {
        log.debug("publishing event: {}", event);
    }
}