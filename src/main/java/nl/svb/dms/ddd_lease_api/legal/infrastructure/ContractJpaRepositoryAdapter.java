package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractSignedEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContractJpaRepositoryAdapter implements ContractDomainRepository {


    @Override
    public void save(ContractFilledOutEvent contractFilledOutEvent) {

    }

    @Override
    public void save(ContractSignedEvent contractSignedEvent) {

    }

    @Override
    public void save(CreditRatingCheckedEvent creditRatingCheckedEvent) {

    }

    @Override
    public Optional<Contract> findQuoteBy(ContractReference contractReference) {
        return Optional.empty();
    }

    @Override
    public Double getCreditRating(ContractReference contractReference) {
        return 0.0;
    }
}
