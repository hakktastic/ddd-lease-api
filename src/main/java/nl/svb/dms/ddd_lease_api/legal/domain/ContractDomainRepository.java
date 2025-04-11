package nl.svb.dms.ddd_lease_api.legal.domain;

import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;

import java.util.Optional;

public interface ContractDomainRepository {

    void save(ContractFilledOutEvent contractFilledOutEvent);

    void save(CreditRatingCheckedEvent creditRatingCheckedEvent);

    Optional<Contract> findContractBy(ContractReference contractReference);

    Double getCreditRating(ContractReference contractReference);
}