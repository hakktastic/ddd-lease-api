package nl.svb.dms.ddd_lease_api.sales.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.QuoteEntity;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteStatus;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "quote")
public class QuoteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID quoteReference;
    private Long leaseDuration;
    private Integer leaseMileage;
    private Double leasePrice;
    @Enumerated(EnumType.STRING)
    private QuoteStatus quoteStatus;

    // TODO create new JPA entity for customer
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private LocalDate customerBirthDate;
    private Double customerYearlyIncome;
    private Boolean customerHasBkrRegistration;

    // TODO create new JPA entity for car
    private String carBrandName;
    private String carModel;
    private Double carCatalogPrice;

    public static QuoteJpaEntity from(Quote quote) {

        final var quoteEntity = quote.getQuoteEntity();
        final var quoteJpaEntity = new QuoteJpaEntity();

        quoteJpaEntity.setLeaseDuration(quoteEntity.getLeaseDuration().leaseDuration());
        quoteJpaEntity.setLeaseMileage(quoteEntity.getLeaseMileage().leaseMileage());
        quoteJpaEntity.setCustomerFirstName(quoteEntity.getCustomerFirstName().customerFirstName());
        quoteJpaEntity.setCustomerLastName(quoteEntity.getCustomerLastName().customerLastName());
        quoteJpaEntity.setCustomerEmail(quoteEntity.getCustomerEmail().customerEmail());
        quoteJpaEntity.setCustomerBirthDate(quoteEntity.getCustomerBirthDate().customerBirthDate());
        quoteJpaEntity.setCustomerYearlyIncome(quoteEntity.getCustomerYearlyIncome().customerYearlyIncome());
        quoteJpaEntity.setCarBrandName(quoteEntity.getCarBrand().carBrand());
        quoteJpaEntity.setCarModel(quoteEntity.getCarModel().carModel());
        quoteJpaEntity.setCarCatalogPrice(quoteEntity.getCarCatalogPrice().carCatalogPrice());
        quoteJpaEntity.setQuoteReference(quoteEntity.getQuoteReference().quoteReference());
        quoteJpaEntity.setLeasePrice(quoteEntity.getLeasePrice().leasePrice());
        quoteJpaEntity.setCustomerHasBkrRegistration(quoteEntity.hasCustomerBkrRegistration());
        quoteJpaEntity.setQuoteStatus(quoteEntity.getQuoteStatus());

        return quoteJpaEntity;
    }


    public Quote toQuote() {

        return Quote.of(
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
                        CustomerHasBkrRegistration.of(this.customerHasBkrRegistration),
                        this.quoteStatus
                )
        );
    }
}
