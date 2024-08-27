package nl.svb.dms.ddd_lease_api.sales.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;

@Getter
@ToString
@RequiredArgsConstructor
public abstract class SalesEvent {

    public final Quote quote;

    public abstract void accept(SalesVisitor salesVisitor) throws QuoteNotFoundException;
}
