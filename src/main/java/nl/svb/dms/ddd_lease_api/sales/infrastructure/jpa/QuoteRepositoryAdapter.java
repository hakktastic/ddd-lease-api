package nl.svb.dms.ddd_lease_api.sales.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class QuoteRepositoryAdapter implements QuoteRepository {

    private final QuoteJpaRepository repository;

    @Override
    public Optional<Quote> save(Quote quote) {

        final var savedEntity = repository.save(QuoteJpaEntity.from(quote));
        return Optional.of(savedEntity.toQuote());
    }
}
