package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.event.InstallmentCalculatedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.SalesEvent;
import nl.svb.dms.ddd_lease_api.sales.infrastructure.jpa.QuoteJpaEntity;
import nl.svb.dms.ddd_lease_api.sales.infrastructure.jpa.QuoteJpaRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
class QuoteRepositoryAdapter implements QuoteDomainRepository {

    private final QuoteJpaRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void save(QuoteFilledOutEvent quoteFilledOutEvent) throws QuoteNotFoundException {

        logSaveEvent(quoteFilledOutEvent);
        final var quoteJpaEntity = QuoteJpaEntity.from(quoteFilledOutEvent.getQuote());
        logSaveJpaEntity(quoteJpaEntity);
        repository.save(quoteJpaEntity);
    }

    @Override
    public void save(InstallmentCalculatedEvent installmentCalculatedEvent) {

        logSaveEvent(installmentCalculatedEvent);
        final var quoteReference = installmentCalculatedEvent.getQuote().getQuoteReference();
        final var quoteJpaEntity = findQuoteJpaEntityBy(quoteReference);
        quoteJpaEntity.setLeasePrice(installmentCalculatedEvent.getQuote().getQuoteEntity().getLeasePrice().leasePrice());
        quoteJpaEntity.setQuoteStatus(installmentCalculatedEvent.getQuote().getQuoteEntity().getQuoteStatus());

        logSaveJpaEntity(quoteJpaEntity);
        repository.save(quoteJpaEntity);
    }

    @Override
    public void save(QuoteSignedEvent quoteSignedEvent) {

        logSaveEvent(quoteSignedEvent);
        final var quoteReference = quoteSignedEvent.getQuote().getQuoteReference();
        final var quoteJpaEntity = findQuoteJpaEntityBy(quoteReference);
        quoteJpaEntity.setQuoteStatus(quoteSignedEvent.getQuote().getQuoteEntity().getQuoteStatus());

        logSaveJpaEntity(quoteJpaEntity);
        repository.save(quoteJpaEntity);
    }

    @Override
    public void publish(QuoteFilledOutEvent quoteFilledOutEvent) {
        logPublishEvent(quoteFilledOutEvent);
        applicationEventPublisher.publishEvent(quoteFilledOutEvent);
    }

    @Override
    public void publish(InstallmentCalculatedEvent installmentCalculatedEvent) {
        logPublishEvent(installmentCalculatedEvent);
        applicationEventPublisher.publishEvent(installmentCalculatedEvent);
    }

    @Override
    public void publish(QuoteSignedEvent quoteSignedEvent) {
        logPublishEvent(quoteSignedEvent);
        applicationEventPublisher.publishEvent(quoteSignedEvent);
    }

    public Optional<Quote> findQuoteBy(QuoteReference quoteReference) {

        final var quoteReferenceUUID = quoteReference.quoteReference();
        final var optionalQuoteJpaEntity = repository.findByQuoteReference(quoteReferenceUUID);

        return optionalQuoteJpaEntity.map(QuoteJpaEntity::toQuote);
    }

    @Override
    public Boolean hasCustomerBkrRegistration(QuoteReference quoteReference) {

        // TODO implementation
        return false;
    }

    @SneakyThrows
    private QuoteJpaEntity findQuoteJpaEntityBy(QuoteReference quoteReference) {

        return repository.findByQuoteReference(quoteReference.quoteReference())
                .orElseThrow(() -> new QuoteNotFoundException(quoteReference));
    }

    private void logSaveEvent(SalesEvent event) {
        log.debug("saving event: {} ", event);
    }

    private void logSaveJpaEntity(QuoteJpaEntity quoteJpaEntity) {
        log.debug("saving jpa entity: {}", quoteJpaEntity);
    }

    private void logPublishEvent(SalesEvent event) {
        log.debug("publishing event: {}", event);
    }
}
