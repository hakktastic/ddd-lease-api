package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainEventPublisher;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainService;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractProvider;
import nl.svb.dms.ddd_lease_api.legal.domain.event.LegalEventPublishVisitor;
import nl.svb.dms.ddd_lease_api.legal.domain.event.LegalEventSaveVisitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringBeanConfiguration {

    @Bean
    ContractDomainService contractDomainService(ContractProvider contractProvider,
                                                LegalEventSaveVisitor legalEventSaveVisitor,
                                                LegalEventPublishVisitor legalEventPublishVisitor,
                                                ContractDomainRepository contractDomainRepository) {

        return new ContractDomainService(contractProvider, legalEventSaveVisitor, legalEventPublishVisitor,
                contractDomainRepository);
    }

    @Bean
    ContractProvider contractProvider(ContractDomainRepository contractDomainRepository) {
        return ContractProvider.of(contractDomainRepository);
    }

    @Bean
    LegalEventSaveVisitor legalEventSaveVisitor(ContractDomainRepository contractDomainRepository) {
        return LegalEventSaveVisitor.of(contractDomainRepository);
    }

    @Bean
    LegalEventPublishVisitor legalEventPublishVisitor(ContractDomainEventPublisher contractDomainEventPublisher) {
        return LegalEventPublishVisitor.of(contractDomainEventPublisher);
    }
}
