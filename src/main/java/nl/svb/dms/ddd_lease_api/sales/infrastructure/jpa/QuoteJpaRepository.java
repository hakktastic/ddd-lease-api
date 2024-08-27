package nl.svb.dms.ddd_lease_api.sales.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuoteJpaRepository extends CrudRepository<QuoteJpaEntity, Long> {

    Optional<QuoteJpaEntity> findByQuoteReference(UUID quoteReference);
}
