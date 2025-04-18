package nl.svb.dms.ddd_lease_api.generic;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configure the JPA repositories used to implement BaseJpaRepository from the Hypersistence Utils
 * instead of Spring's default SimpleJpaRepository.
 * <p>
 * See:
 * <a href="https://vladmihalcea.com/basejparepository-hypersistence-utils">The awesome
 * BaseJpaRepository from Hypersistence Utils</a>
 * <a href="https://vladmihalcea.com/best-spring-data-jparepository">The JPA save antipattern</a>
 * <a href="https://vladmihalcea.com/spring-data-findall-anti-pattern">The JPA findAll
 * antipattern</a>
 * </p>
 */
@Configuration
@EnableJpaRepositories(
    repositoryBaseClass = BaseJpaRepositoryImpl.class,
    basePackages = {
        "nl.svb.dms.ddd_lease_api.sales.infrastructure",
        "nl.svb.dms.ddd_lease_api.legal.infrastructure"
    })
public class HypersistenceJpaRepositoryConfig {

}