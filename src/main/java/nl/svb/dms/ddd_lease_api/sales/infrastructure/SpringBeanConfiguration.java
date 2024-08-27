package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainService;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteProvider;
import nl.svb.dms.ddd_lease_api.sales.domain.event.SalesEventVisitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

    @Bean
    public QuoteDomainService quoteDomainService(QuoteProvider quoteProvider, SalesEventVisitor salesEventVisitor) {
        return new QuoteDomainService(quoteProvider, salesEventVisitor);
    }

    @Bean
    public SalesEventVisitor salesEventVisitor(QuoteDomainRepository quoteDomainRepository) {
        return SalesEventVisitor.of(quoteDomainRepository);
    }

    @Bean
    public QuoteProvider quoteProvider(QuoteDomainRepository quoteDomainRepository) {
        return QuoteProvider.of(quoteDomainRepository);
    }
}
