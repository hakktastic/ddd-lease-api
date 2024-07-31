package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer;

import java.time.LocalDate;

public record CustomerBirthDate(LocalDate customerBirthDate) {

    public static CustomerBirthDate of(LocalDate customerBirthDate) {
        return new CustomerBirthDate(customerBirthDate);
    }
}
