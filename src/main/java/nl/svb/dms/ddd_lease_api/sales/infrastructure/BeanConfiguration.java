package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import nl.svb.dms.ddd_lease_api.sales.domain.DomainQuoteService;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BeanConfiguration {

    @Bean
    QuoteService quoteService(final QuoteRepository quoteRepository) {
        return new DomainQuoteService(quoteRepository);
    }
}
