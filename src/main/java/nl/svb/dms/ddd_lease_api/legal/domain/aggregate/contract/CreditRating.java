package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract;

public record CreditRating(Double creditRating) {

    public static CreditRating of(Double creditRating) {
        return new CreditRating(creditRating);
    }
}
