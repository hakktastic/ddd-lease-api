package nl.svb.dms.ddd_lease_api.sales.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.*;

@Data
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
class QuoteDTO {

    private Long duration;

    private Long mileage;

    private Double leasePrice;

    private String car;

    private String customer;

    public static QuoteDTO from(final Quote quote) {

        final var quoteEntity = quote.getQuoteEntity();

        return QuoteDTO.of(
                quoteEntity.getDuration().getDuration(),
                quoteEntity.getMileage().getMileage(),
                quoteEntity.getLeasePrice().getLeasePrice(),
                quoteEntity.getCar().getCar(),
                quoteEntity.getCustomer().getCustomer()
        );
    }

    public Quote toQuote() {

        final var quote = Quote.of();
        quote.setQuoteEntity(QuoteEntity.of(
                Duration.of(this.duration),
                Mileage.of(this.mileage),
                LeasePrice.of(0.0),
                Car.of(this.car),
                Customer.of(this.customer)
        ));

        return quote;
    }
}


