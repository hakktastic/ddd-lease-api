package nl.svb.dms.ddd_lease_api.sales.domain.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class FillOutQuoteCommand implements SalesCommand {

    private final QuoteReference quoteReference;
}
