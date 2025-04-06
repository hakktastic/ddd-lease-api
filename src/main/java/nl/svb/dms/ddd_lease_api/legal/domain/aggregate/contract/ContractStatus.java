package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public enum ContractStatus {
    ACCEPTED,
    FILLED_OUT,
    SIGNED,
    CREATED, REJECTED_CREDIT_SCORE
}