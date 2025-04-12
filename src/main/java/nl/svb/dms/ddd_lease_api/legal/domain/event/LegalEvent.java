package nl.svb.dms.ddd_lease_api.legal.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;

@Getter
@ToString
@RequiredArgsConstructor
public abstract class LegalEvent {

    private final Contract contract;

    public abstract void accept(ContractDomainRepository contractDomainRepository);
}