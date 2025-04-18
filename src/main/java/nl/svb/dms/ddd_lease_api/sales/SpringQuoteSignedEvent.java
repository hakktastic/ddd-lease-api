package nl.svb.dms.ddd_lease_api.sales;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class SpringQuoteSignedEvent {

  private final UUID quoteReference;
  private final Long leaseDuration;
  private final int leaseMileage;
  private final String customerFirstName;
  private final String customerLastName;
  private final String customerEmail;
  private final LocalDate customerBirthDate;
  private final Double customerYearlyIncome;
  private final String carBrand;
  private final String carModel;
  private final Double carCatalogPrice;
  private final Double leasePrice;

  public static SpringQuoteSignedEvent from(QuoteSignedEvent quoteSignedEvent) {

    final var quoteEntity = quoteSignedEvent.getQuote().getQuoteEntity();

    return SpringQuoteSignedEvent.of(
        quoteEntity.getQuoteReference().quoteReference(),
        quoteEntity.getLeaseDuration().leaseDuration(),
        quoteEntity.getLeaseMileage().leaseMileage(),
        quoteEntity.getCustomerFirstName().customerFirstName(),
        quoteEntity.getCustomerLastName().customerLastName(),
        quoteEntity.getCustomerEmail().customerEmail(),
        quoteEntity.getCustomerBirthDate().customerBirthDate(),
        quoteEntity.getCustomerYearlyIncome().customerYearlyIncome(),
        quoteEntity.getCarBrand().carBrand(),
        quoteEntity.getCarModel().carModel(),
        quoteEntity.getCarCatalogPrice().carCatalogPrice(),
        quoteEntity.getLeasePrice().leasePrice()
    );
  }
}