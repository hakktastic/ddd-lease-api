package nl.svb.dms.ddd_lease_api.sales.domain;

import java.util.Optional;

public interface QuoteService {

    Optional<Quote> fillOut(Quote quote);

    void calculateInstallment();

    void sign();
}
