package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CreditRating(Double creditRating) {

    public static CreditRating of(Double creditRating) {
        return new CreditRating(creditRating);
    }
}