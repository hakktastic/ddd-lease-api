package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FillOutQuoteResult {

    private final QuoteReference quoteReference;
    private final QuoteStatus quoteStatus;
}
