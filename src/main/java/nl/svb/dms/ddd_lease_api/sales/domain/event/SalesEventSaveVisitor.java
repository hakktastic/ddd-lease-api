package nl.svb.dms.ddd_lease_api.sales.domain.event;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;

@RequiredArgsConstructor(staticName = "of")
public class SalesEventSaveVisitor implements SalesVisitor {

    private final QuoteDomainRepository quoteDomainRepository;

    @Override
    public void visit(QuoteFilledOutEvent quoteFilledOutEvent) throws QuoteNotFoundException {
        quoteDomainRepository.save(quoteFilledOutEvent);
    }

    @Override
    public void visit(InstallmentCalculatedEvent installmentCalculatedEvent) {
        quoteDomainRepository.save(installmentCalculatedEvent);
    }

    @Override
    public void visit(QuoteSignedEvent quoteSignedEvent) {
        quoteDomainRepository.save(quoteSignedEvent);
    }
}