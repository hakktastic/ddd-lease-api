package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainEventPublisher;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainService;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteProvider;
import nl.svb.dms.ddd_lease_api.sales.domain.event.SalesEventPublishVisitor;
import nl.svb.dms.ddd_lease_api.sales.domain.event.SalesEventSaveVisitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringBeanConfiguration {

    @Bean
    QuoteDomainService quoteDomainService(QuoteProvider quoteProvider, SalesEventSaveVisitor saveSalesEventVisitor,
                                          SalesEventPublishVisitor salesEventPublishVisitor) {
        return new QuoteDomainService(quoteProvider, saveSalesEventVisitor, salesEventPublishVisitor);
    }

    @Bean
    SalesEventSaveVisitor salesEventVisitor(QuoteDomainRepository quoteDomainRepository) {
        return SalesEventSaveVisitor.of(quoteDomainRepository);
    }

    @Bean
    SalesEventPublishVisitor salesEventPublishVisitor(QuoteDomainEventPublisher quoteDomainEventPublisher) {
        return SalesEventPublishVisitor.of(quoteDomainEventPublisher);
    }

    @Bean
    QuoteProvider quoteProvider(QuoteDomainRepository quoteDomainRepository) {
        return QuoteProvider.of(quoteDomainRepository);
    }
}
