package nl.svb.dms.ddd_lease_api.legal.domain;

import java.util.Optional;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;

public interface ContractDomainRepository {

  void handle(ContractFilledOutEvent contractFilledOutEvent);

  void handle(CreditRatingCheckedEvent creditRatingCheckedEvent);

  Optional<Contract> findContractBy(ContractReference contractReference);

  Double getCreditRating(ContractReference contractReference);
}