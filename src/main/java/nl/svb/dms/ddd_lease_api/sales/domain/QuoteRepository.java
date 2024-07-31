package nl.svb.dms.ddd_lease_api.sales.domain;

import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;

import java.util.Optional;

public interface QuoteRepository {

    Optional<Quote> save();
}
