package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContractJpaRepository extends CrudRepository<ContractJpaEntity, Long> {

    Optional<ContractJpaEntity> findByContractReference(UUID contractReference);
}