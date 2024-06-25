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
import nl.svb.dms.ddd_lease_api.sales.domain.Quote;

@Getter
@Setter
@ToString
@Entity(name = "quote")
public class QuoteJpaEntity {

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

        final var quoteEntity = new QuoteJpaEntity();
        quoteEntity.setCar(quote.getCar());
        quoteEntity.setLeasePrice(quote.getLeasePrice());
        quoteEntity.setMileage(quote.getMileage());
        quoteEntity.setDuration(quote.getDuration());
        quoteEntity.setCustomer(quote.getCustomer());

        return quoteEntity;
    }

    public Quote toQuote() {

        final var quote = Quote.of();
        quote.setDuration(this.duration);
        quote.setMileage(this.mileage);
        quote.setLeasePrice(this.leasePrice);
        quote.setCar(this.car);
        quote.setCustomer(this.customer);

        return quote;
    }
}
