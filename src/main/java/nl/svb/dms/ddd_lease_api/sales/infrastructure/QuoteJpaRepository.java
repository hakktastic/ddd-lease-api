package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface QuoteJpaRepository extends MongoRepository<QuoteDocument, ObjectId> {

  Optional<QuoteDocument> findByQuoteReference(UUID quoteReference);
}