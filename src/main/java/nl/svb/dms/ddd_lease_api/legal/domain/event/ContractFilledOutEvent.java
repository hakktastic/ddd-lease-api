package nl.svb.dms.ddd_lease_api.legal.domain.event;

import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;

public class ContractFilledOutEvent extends LegalEvent {

    public ContractFilledOutEvent(Contract contract) {
        super(contract);
    }

    @Override
    public void accept(ContractDomainRepository contractDomainRepository) {
        contractDomainRepository.handle(this);
    }
}