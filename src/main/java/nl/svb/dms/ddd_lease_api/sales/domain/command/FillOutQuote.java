package nl.svb.dms.ddd_lease_api.sales.domain.command;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseMileage;

@RequiredArgsConstructor(staticName = "of")
public class FillOutQuote {

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
}
