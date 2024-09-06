package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainEventPublisher;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainService;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteProvider;
import nl.svb.dms.ddd_lease_api.sales.domain.event.SalesEventVisitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringBeanConfiguration {

    @Bean
    QuoteDomainService quoteDomainService(QuoteProvider quoteProvider, SalesEventVisitor salesEventVisitor) {
        return new QuoteDomainService(quoteProvider, salesEventVisitor);
    }

    @Bean
    SalesEventVisitor salesEventVisitor(QuoteDomainRepository quoteDomainRepository, QuoteDomainEventPublisher quoteDomainEventPublisher) {
        return SalesEventVisitor.of(quoteDomainRepository, quoteDomainEventPublisher);
    }

    @Bean
    QuoteProvider quoteProvider(QuoteDomainRepository quoteDomainRepository) {
        return QuoteProvider.of(quoteDomainRepository);
    }
}
