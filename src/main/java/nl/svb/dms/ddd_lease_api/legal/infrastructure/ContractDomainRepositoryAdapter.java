package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.SpringCreditRatingCheckedEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.LegalEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
class ContractDomainRepositoryAdapter implements ContractDomainRepository {

  private final ContractJpaRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public void handle(ContractFilledOutEvent contractFilledOutEvent) {

    final var contractJpaEntity = ContractJpaEntity.from(contractFilledOutEvent.getContract());
    persistEvent(contractJpaEntity);
    publishEvent(contractFilledOutEvent);
  }

  @Override
  public void handle(CreditRatingCheckedEvent creditRatingCheckedEvent) {

    final var contractReference = creditRatingCheckedEvent.getContract().getContractEntity()
        .getContractReference();
    final var contractJpaEntity = findContractJpaEntityBy(contractReference);

    contractJpaEntity.setContractStatus(
        creditRatingCheckedEvent.getContract().getContractEntity().getContractStatus());
    contractJpaEntity.setCreditRating(
        creditRatingCheckedEvent.getContract().getContractEntity().getCreditRating()
            .creditRating());

    persistEvent(contractJpaEntity);
    publishEvent(creditRatingCheckedEvent);
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

  private void persistEvent(ContractJpaEntity contractJpaEntity) {
    log.debug("saving jpa entity: {}", contractJpaEntity);
    repository.persist(contractJpaEntity);
  }

  private void publishEvent(LegalEvent legalEvent) {
    log.debug("publishing event: {}", legalEvent);

    if (legalEvent instanceof CreditRatingCheckedEvent creditRatingCheckedEvent) {
      eventPublisher.publishEvent(
          SpringCreditRatingCheckedEvent.from(creditRatingCheckedEvent)
      );
    } else {
      eventPublisher.publishEvent(legalEvent);
    }

  }

  @SneakyThrows
  private ContractJpaEntity findContractJpaEntityBy(ContractReference contractReference) {

    return repository.findByContractReference(contractReference.contractReference())
        .orElseThrow(() -> new ContractNotFoundException(contractReference));
  }
}