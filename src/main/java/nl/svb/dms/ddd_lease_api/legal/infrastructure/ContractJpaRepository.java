package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractJpaRepository extends CrudRepository<ContractJpaEntity, Long> {
}
