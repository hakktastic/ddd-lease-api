package nl.svb.dms.ddd_lease_api.sales.domain.event;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;

@RequiredArgsConstructor(staticName = "of")
public class SalesEventVisitor implements SalesVisitor {

    private final QuoteDomainRepository quoteDomainRepository;


    @Override
    public void visit(QuoteFilledOutEvent quoteFilledOutEvent) throws QuoteNotFoundException {

        quoteDomainRepository.save(quoteFilledOutEvent);
        quoteDomainRepository.publish(quoteFilledOutEvent);
    }

    @Override
    public void visit(InstallmentCalculatedEvent installmentCalculatedEvent) {

        quoteDomainRepository.save(installmentCalculatedEvent);
        quoteDomainRepository.publish(installmentCalculatedEvent);
    }

    @Override
    public void visit(QuoteSignedEvent quoteSignedEvent) {

        quoteDomainRepository.save(quoteSignedEvent);
        quoteDomainRepository.publish(quoteSignedEvent);
    }
}