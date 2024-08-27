package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;

@RequiredArgsConstructor(staticName = "of")
public class QuoteProvider {

    private final QuoteDomainRepository quoteDomainRepository;

    public Quote findQuote(QuoteReference quoteReference) throws QuoteNotFoundException {
        return quoteDomainRepository.findQuoteBy(quoteReference)
                .orElseThrow(() -> new QuoteNotFoundException(quoteReference));
    }

    public Boolean hasCustomerBkrRegistration(QuoteReference quoteReference) {
        return quoteDomainRepository.hasCustomerBkrRegistration(quoteReference);
    }
}
