package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.LegalEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@RequiredArgsConstructor
class ContractJpaRepositoryAdapter implements ContractDomainRepository {

    private final ContractJpaRepository repository;

    @Override
    public void save(ContractFilledOutEvent contractFilledOutEvent) {

        logSaveEvent(contractFilledOutEvent);
        final var contractJpaEntity = ContractJpaEntity.from(contractFilledOutEvent.getContract());
        logSaveJpaEntity(contractJpaEntity);
        repository.save(contractJpaEntity);
    }

    @Override
    public void save(CreditRatingCheckedEvent creditRatingCheckedEvent) {

        logSaveEvent(creditRatingCheckedEvent);
        final var contractReference = creditRatingCheckedEvent.getContract().getContractReference();

        final var contractJpaEntity = findContractJpaEntityBy(contractReference);
        contractJpaEntity.setContractStatus(creditRatingCheckedEvent.getContract().getContractEntity().getContractStatus());
        contractJpaEntity.setCreditRating(creditRatingCheckedEvent.getContract().getContractEntity().getCreditRating().creditRating());

        logSaveJpaEntity(contractJpaEntity);
        repository.save(contractJpaEntity);

    }

    @Override
    public Optional<Contract> findContractBy(ContractReference contractReference) {

        final var contractReferenceUUID = contractReference.contractReference();
        final var optionalContractJpaEntity = repository.findByContractReference(contractReferenceUUID);

        return optionalContractJpaEntity.map(ContractJpaEntity::toContract);
    }

    @Override
    public Double getCreditRating(ContractReference contractReference) {

        // just returning a random credit rating
        // normally should call an external service
        return ThreadLocalRandom.current().nextDouble(80, 101);
    }

    @SneakyThrows
    private ContractJpaEntity findContractJpaEntityBy(ContractReference contractReference) {

        return repository.findByContractReference(contractReference.contractReference())
                .orElseThrow(() -> new ContractNotFoundException(contractReference));
    }

    private void logSaveEvent(LegalEvent event) {

        log.debug("saving event: {} ", event);
    }

    private void logSaveJpaEntity(ContractJpaEntity contractJpaEntity) {

        log.debug("saving jpa entity: {}", contractJpaEntity);
    }
}