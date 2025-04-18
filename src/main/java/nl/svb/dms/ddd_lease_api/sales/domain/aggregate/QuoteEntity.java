package nl.svb.dms.ddd_lease_api.sales.domain.aggregate;

import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerBirthDate;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerEmail;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerFirstName;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerLastName;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerYearlyIncome;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteStatus;

@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class QuoteEntity {

  public static final int MAX_ALLOWED_PERCENTAGE_OF_YEARLY_INCOME = 10;
  public static final int ONE_HUNDRED_PERCENT = 100;
  private static final Integer MINIMUM_REQUIRED_AGE = 24;
  private static final Double MINIMUM_YEARLY_WELFARE_INCOME = 12000.00;

  private final QuoteReference quoteReference;
  private final LeaseDuration leaseDuration;
  private final LeaseMileage leaseMileage;
  private final CustomerFirstName customerFirstName;
  private final CustomerLastName customerLastName;
  private final CustomerEmail customerEmail;
  private final CustomerBirthDate customerBirthDate;
  private final CustomerYearlyIncome customerYearlyIncome;
  private final CarBrand carBrand;
  private final CarModel carModel;
  private final CarCatalogPrice carCatalogPrice;
  private LeasePrice leasePrice;

  @Setter
  private QuoteStatus quoteStatus;

  public boolean customerHasMinimumRequiredAge() {
    return getCustomerAge() >= MINIMUM_REQUIRED_AGE;
  }

  public boolean customerHasMinimumRequiredIncome() {
    return customerYearlyIncome.customerYearlyIncome() >= MINIMUM_YEARLY_WELFARE_INCOME;
  }

  public boolean installmentAmountIsAllowed() {
    final var percentageOfYearlyIncome =
        (this.leasePrice.leasePrice() / this.customerYearlyIncome.customerYearlyIncome())
            * ONE_HUNDRED_PERCENT;
    return percentageOfYearlyIncome <= MAX_ALLOWED_PERCENTAGE_OF_YEARLY_INCOME;
  }

  public void calculateLeasePrice() {
    this.leasePrice = LeasePrice.of(
        (carCatalogPrice.carCatalogPrice() * 1.1) / leaseDuration.leaseDuration());
  }

  private Integer getCustomerAge() {
    return Period.between(customerBirthDate.customerBirthDate(), LocalDate.now()).getYears();
  }
}