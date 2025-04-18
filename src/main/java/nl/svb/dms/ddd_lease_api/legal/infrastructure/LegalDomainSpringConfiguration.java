package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainService;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration to include necessary Ports so that Adapters can use them in Spring context.
 */
@Configuration
class LegalDomainSpringConfiguration {

  @Bean
  ContractDomainService contractDomainService(ContractProvider contractProvider,
      ContractDomainRepository contractDomainRepository) {
    return new ContractDomainService(contractProvider, contractDomainRepository);
  }

  @Bean
  ContractProvider contractProvider(ContractDomainRepository contractDomainRepository) {
    return ContractProvider.of(contractDomainRepository);
  }
}