package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.QuoteEntity;
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
@Setter
@ToString
@Entity(name = "quote")
class QuoteJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private UUID quoteReference;
  private Long leaseDuration;
  private Integer leaseMileage;
  private Double leasePrice;
  @Enumerated(EnumType.STRING)
  private QuoteStatus quoteStatus;

  private String customerFirstName;
  private String customerLastName;
  private String customerEmail;
  private LocalDate customerBirthDate;
  private Double customerYearlyIncome;

  private String carBrandName;
  private String carModel;
  private Double carCatalogPrice;

  @Version
  private int revision;

  static QuoteJpaEntity from(Quote quote) {

    final var quoteEntity = quote.getQuoteEntity();
    final var quoteJpaEntity = new QuoteJpaEntity();

    quoteJpaEntity.setQuoteReference(quote.getQuoteReference().quoteReference());
    quoteJpaEntity.setLeaseDuration(quoteEntity.getLeaseDuration().leaseDuration());
    quoteJpaEntity.setLeaseMileage(quoteEntity.getLeaseMileage().leaseMileage());
    quoteJpaEntity.setCustomerFirstName(quoteEntity.getCustomerFirstName().customerFirstName());
    quoteJpaEntity.setCustomerLastName(quoteEntity.getCustomerLastName().customerLastName());
    quoteJpaEntity.setCustomerEmail(quoteEntity.getCustomerEmail().customerEmail());
    quoteJpaEntity.setCustomerBirthDate(quoteEntity.getCustomerBirthDate().customerBirthDate());
    quoteJpaEntity.setCustomerYearlyIncome(
        quoteEntity.getCustomerYearlyIncome().customerYearlyIncome());
    quoteJpaEntity.setCarBrandName(quoteEntity.getCarBrand().carBrand());
    quoteJpaEntity.setCarModel(quoteEntity.getCarModel().carModel());
    quoteJpaEntity.setCarCatalogPrice(quoteEntity.getCarCatalogPrice().carCatalogPrice());
    quoteJpaEntity.setLeasePrice(quoteEntity.getLeasePrice().leasePrice());
    quoteJpaEntity.setQuoteStatus(quoteEntity.getQuoteStatus());

    return quoteJpaEntity;
  }

  Quote toQuote() {

    return Quote.of(QuoteReference.of(this.quoteReference),
        QuoteEntity.of(
            QuoteReference.of(this.quoteReference),
            LeaseDuration.of(this.leaseDuration),
            LeaseMileage.of(this.leaseMileage),
            CustomerFirstName.of(this.customerFirstName),
            CustomerLastName.of(this.customerLastName),
            CustomerEmail.of(this.customerEmail),
            CustomerBirthDate.of(this.customerBirthDate),
            CustomerYearlyIncome.of(this.customerYearlyIncome),
            CarBrand.of(this.carBrandName),
            CarModel.of(this.carModel),
            CarCatalogPrice.of(this.carCatalogPrice),
            LeasePrice.of(this.leasePrice),
            this.quoteStatus
        )
    );
  }
}