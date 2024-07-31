package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer;

import jakarta.validation.constraints.Email;

public record CustomerEmail(String customerEmail) {

    @Email
    public CustomerEmail of(String customerEmail) {
        return new CustomerEmail(customerEmail);
    }
}
