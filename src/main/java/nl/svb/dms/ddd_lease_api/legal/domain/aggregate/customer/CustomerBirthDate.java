package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer;

import org.jmolecules.ddd.annotation.ValueObject;

import java.time.LocalDate;

@ValueObject
public record CustomerBirthDate(LocalDate customerBirthDate) {

    public static CustomerBirthDate of(LocalDate customerBirthDate) {
        return new CustomerBirthDate(customerBirthDate);
    }
}