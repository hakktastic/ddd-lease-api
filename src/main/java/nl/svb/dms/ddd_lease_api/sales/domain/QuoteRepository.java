package nl.svb.dms.ddd_lease_api.sales.domain;

import java.util.Optional;

public interface QuoteRepository {
    Optional<Quote> save(Quote quote);
}
