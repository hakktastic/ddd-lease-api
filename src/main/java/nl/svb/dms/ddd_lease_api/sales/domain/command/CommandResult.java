package nl.svb.dms.ddd_lease_api.sales.domain.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteStatus;
import nl.svb.dms.ddd_lease_api.sales.domain.event.SalesEvent;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class CommandResult {

    private final Quote quote;
    private final QuoteStatus status;
    private final SalesEvent event;
}
