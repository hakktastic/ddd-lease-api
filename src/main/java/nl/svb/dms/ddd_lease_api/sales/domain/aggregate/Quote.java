package nl.svb.dms.ddd_lease_api.sales.domain.aggregate;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.FillOutQuoteResult;
import nl.svb.dms.ddd_lease_api.sales.domain.command.FillOutQuote;

@RequiredArgsConstructor(staticName = "of")
public final class Quote {

    private final QuoteEntity quoteEntity;

    public FillOutQuoteResult handleCommand(final FillOutQuote fillOutQuote) {


        return null;
    }
}
