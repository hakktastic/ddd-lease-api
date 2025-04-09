package nl.svb.dms.ddd_lease_api.legal.domain;

import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;
import org.jmolecules.architecture.hexagonal.Port;

@Port
public interface ContractDomainEventPublisher {

    void publish(ContractFilledOutEvent contractFilledOutEvent);

    void publish(CreditRatingCheckedEvent creditRatingCheckedEvent);
}