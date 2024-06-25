package nl.svb.dms.ddd_lease_api.sales.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface QuoteJpaRepository extends CrudRepository<QuoteJpaEntity, Long> {
}
