package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface QuoteJpaRepository extends CrudRepository<ContractJpaEntity, Long> {

    Optional<ContractJpaEntity> findByQuoteReference(UUID quoteReference);
}