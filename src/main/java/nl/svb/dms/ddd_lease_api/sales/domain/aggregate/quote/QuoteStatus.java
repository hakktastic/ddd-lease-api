package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote;

public enum QuoteStatus {
    CREATED,
    FILLED_OUT,
    CALCULATED,
    SIGNED,
    REJECTED_MIN_AGE,
    REJECTED_MAX_PERCENTAGE_OF_YEARLY_INCOME,
    REJECTED_MIN_INCOME
}