package nl.svb.dms.ddd_lease_api.sales.domain.event;

import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;

public class QuoteSignedEvent extends SalesEvent {

    public QuoteSignedEvent(Quote quote) {
        super(quote);
    }

    @Override
    public void accept(SalesVisitor salesVisitor) {
        salesVisitor.visit(this);
    }
}
