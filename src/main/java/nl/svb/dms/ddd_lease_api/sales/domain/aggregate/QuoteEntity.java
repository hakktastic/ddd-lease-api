package nl.svb.dms.ddd_lease_api.sales.domain.aggregate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeasePrice;

import java.time.LocalDate;
import java.time.Period;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class QuoteEntity {

    private static final Integer MINIMUM_REQUIRED_AGE = 24;

    private final LeaseDuration leaseDuration;
    private final LeaseMileage leaseMileage;
    private final CustomerFirstName customerFirstName;
    private final CustomerLastName customerLastName;
    private final CustomerEmail customerEmail;
    private final CustomerBirthDate customerBirthDate;
    private final CustomerYearlyIncome customerYearlyIncome;
    private final CarBrand brandName;
    private final CarModel model;
    private final CarCatalogPrice carCatalogPrice;

    private LeasePrice leasePrice;

    public boolean hasMinimumRequiredAge() {
        return getCustomerAge() >= MINIMUM_REQUIRED_AGE;
    }

    private Integer getCustomerAge() {
        return Period.between(customerBirthDate.customerBirthDate(), LocalDate.now()).getYears();
    }
}
