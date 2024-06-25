package nl.svb.dms.ddd_lease_api.sales.domain;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DomainQuoteService implements QuoteService {

    private final QuoteRepository quoteRepository;

    @Override
    public Optional<Quote> fillOut(Quote quote) {

        return quoteRepository.save(quote);
    }

    @Override
    public void calculateInstallment() {

    }

    @Override
    public void sign() {

    }
}
