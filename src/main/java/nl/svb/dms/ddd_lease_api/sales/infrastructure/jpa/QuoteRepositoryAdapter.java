package nl.svb.dms.ddd_lease_api.sales.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class QuoteRepositoryAdapter implements QuoteRepository {

    private final QuoteJpaRepository repository;

    @Override
    public Optional<Quote> save() {

        // TODO implementation
        return Optional.empty();
    }
}
