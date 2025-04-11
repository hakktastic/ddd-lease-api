package nl.svb.dms.ddd_lease_api.legal.domain;

import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;

public interface ContractDomainEventPublisher {

    void publish(ContractFilledOutEvent contractFilledOutEvent);

    void publish(CreditRatingCheckedEvent creditRatingCheckedEvent);
}