package nl.svb.dms.ddd_lease_api.sales.infrastructure.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.*;

@Getter
@Setter
@ToString
@Entity(name = "quote")
class QuoteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Positive
    private Long duration;

    @Positive
    private long mileage;

    @PositiveOrZero
    private Double leasePrice;

    @NotNull
    private String car;

    @NotNull
    private String customer;

    public static QuoteJpaEntity from(final Quote quote) {

        final var quoteJpaEntity = new QuoteJpaEntity();
        final var quoteDomainEntity = quote.getQuoteEntity();

        quoteJpaEntity.setCar(quoteDomainEntity.getCar().getCar());
        quoteJpaEntity.setLeasePrice(quoteDomainEntity.getLeasePrice().getLeasePrice());
        quoteJpaEntity.setMileage(quoteDomainEntity.getMileage().getMileage());
        quoteJpaEntity.setDuration(quoteDomainEntity.getDuration().getDuration());
        quoteJpaEntity.setCustomer(quoteDomainEntity.getCustomer().getCustomer());

        return quoteJpaEntity;
    }

    public Quote toQuote() {

        final var quote = Quote.of();
        quote.setQuoteEntity(QuoteEntity.of(
                Duration.of(this.duration),
                Mileage.of(this.mileage),
                LeasePrice.of(this.leasePrice),
                Car.of(this.car),
                Customer.of(this.customer)
        ));

        return quote;
    }
}
