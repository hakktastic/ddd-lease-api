package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public record ContractReference(UUID contractReference) {

    public static ContractReference of() {
        return new ContractReference(UUID.randomUUID());
    }

    public static ContractReference of(UUID quoteReference) {
        return new ContractReference(quoteReference);
    }
}