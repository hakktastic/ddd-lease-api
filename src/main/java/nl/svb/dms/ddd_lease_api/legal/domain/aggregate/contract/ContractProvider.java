package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;

@RequiredArgsConstructor(staticName = "of")
public class ContractProvider {

    private final ContractDomainRepository contractDomainRepository;

    public Contract findContract(ContractReference contractReference) {
        return contractDomainRepository.findQuoteBy(contractReference).orElse(null);
    }
}
