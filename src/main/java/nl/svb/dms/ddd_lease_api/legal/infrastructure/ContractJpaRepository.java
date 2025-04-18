package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ContractJpaRepository extends MongoRepository<ContractDocument, ObjectId> {

  Optional<ContractDocument> findByContractReference(UUID contractReference);
}