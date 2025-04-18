package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 * <a href="https://vladmihalcea.com/basejparepository-hypersistence-utils">The awesome
 * BaseJpaRepository from Hypersistence Utils</a>
 * <a href="https://vladmihalcea.com/best-spring-data-jparepository">The JPA save antipattern</a>
 * <a href="https://vladmihalcea.com/spring-data-findall-anti-pattern">The JPA findAll
 * antipattern</a>
 */
@Repository
interface QuoteJpaRepository extends BaseJpaRepository<QuoteJpaEntity, Long> {

  Optional<QuoteJpaEntity> findByQuoteReference(UUID quoteReference);
}