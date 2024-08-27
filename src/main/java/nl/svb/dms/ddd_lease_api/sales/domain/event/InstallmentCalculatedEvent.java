package nl.svb.dms.ddd_lease_api.sales.domain.event;

import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;

public class InstallmentCalculatedEvent extends SalesEvent {

    public InstallmentCalculatedEvent(Quote quote) {
        super(quote);
    }

    @Override
    public void accept(SalesVisitor salesVisitor) {

        salesVisitor.visit(this);
    }
}
