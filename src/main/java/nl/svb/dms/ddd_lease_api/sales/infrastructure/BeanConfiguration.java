package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import nl.svb.dms.ddd_lease_api.sales.domain.QuoteRepository;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO check if this class is needed, since I don't have a DomainService interface anymore
@Configuration
class BeanConfiguration {

    @Bean
    QuoteService quoteService(final QuoteRepository quoteRepository) {

        return new QuoteService(quoteRepository);
    }
}
