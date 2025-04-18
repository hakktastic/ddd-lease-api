package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainService;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration to include necessary Ports so that Adapters can use them in Spring context.
 */
@Configuration
class SalesDomainConfiguration {

  @Bean
  QuoteDomainService quoteDomainService(QuoteProvider quoteProvider,
      QuoteDomainRepository quoteDomainRepository) {
    return new QuoteDomainService(quoteProvider, quoteDomainRepository);
  }

  @Bean
  QuoteProvider quoteProvider(QuoteDomainRepository quoteDomainRepository) {
    return QuoteProvider.of(quoteDomainRepository);
  }
}