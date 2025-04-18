package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.SpringQuoteSignedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.event.InstallmentCalculatedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
class QuoteDomainRepositoryAdapter implements QuoteDomainRepository {

  private final ApplicationEventPublisher eventPublisher;
  private final QuoteJpaRepository repository;

  @Override
  public void handle(QuoteFilledOutEvent quoteFilledOutEvent) {

    final var quoteJpaEntity = QuoteJpaEntity.from(quoteFilledOutEvent.getQuote());
    persistEvent(quoteJpaEntity);
  }

  @Override
  public void handle(InstallmentCalculatedEvent installmentCalculatedEvent) {

    final var quoteReference = installmentCalculatedEvent.getQuote().getQuoteReference();
    final var quoteJpaEntity = findQuoteJpaEntityBy(quoteReference);
    quoteJpaEntity.setLeasePrice(
        installmentCalculatedEvent.getQuote().getQuoteEntity().getLeasePrice().leasePrice());
    quoteJpaEntity.setQuoteStatus(
        installmentCalculatedEvent.getQuote().getQuoteEntity().getQuoteStatus());

    persistEvent(quoteJpaEntity);
  }

  @Override
  public void handle(QuoteSignedEvent quoteSignedEvent) {

    final var quoteReference = quoteSignedEvent.getQuote().getQuoteReference();
    final var quoteJpaEntity = findQuoteJpaEntityBy(quoteReference);
    quoteJpaEntity.setQuoteStatus(quoteSignedEvent.getQuote().getQuoteEntity().getQuoteStatus());

    persistEvent(quoteJpaEntity);
    publishEvent(quoteSignedEvent);
  }

  private void persistEvent(QuoteJpaEntity quoteJpaEntity) {
    log.debug("saving jpa entity: {}", quoteJpaEntity);
    repository.persist(quoteJpaEntity);
  }

  private void publishEvent(QuoteSignedEvent quoteSignedEvent) {
    log.debug("publishing event: {}", quoteSignedEvent);
    eventPublisher.publishEvent(SpringQuoteSignedEvent.from(quoteSignedEvent));
  }

  public Optional<Quote> findQuoteBy(QuoteReference quoteReference) {

    final var quoteReferenceUUID = quoteReference.quoteReference();
    final var optionalQuoteJpaEntity = repository.findByQuoteReference(quoteReferenceUUID);

    return optionalQuoteJpaEntity.map(QuoteJpaEntity::toQuote);
  }

  @SneakyThrows
  private QuoteJpaEntity findQuoteJpaEntityBy(QuoteReference quoteReference) {

    return repository.findByQuoteReference(quoteReference.quoteReference())
        .orElseThrow(() -> new QuoteNotFoundException(quoteReference));
  }
}