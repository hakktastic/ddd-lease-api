package nl.svb.dms.ddd_lease_api.legal.domain;

import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractSignedEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;
import org.jmolecules.architecture.hexagonal.Port;
import org.jmolecules.ddd.annotation.Repository;

import java.util.Optional;

@Port
@Repository
public interface ContractDomainRepository {

    void save(ContractFilledOutEvent contractFilledOutEvent);

    void save(ContractSignedEvent contractSignedEvent);

    void save(CreditRatingCheckedEvent creditRatingCheckedEvent);

    Optional<Contract> findQuoteBy(ContractReference contractReference);

    Double getCreditRating(ContractReference contractReference);
}