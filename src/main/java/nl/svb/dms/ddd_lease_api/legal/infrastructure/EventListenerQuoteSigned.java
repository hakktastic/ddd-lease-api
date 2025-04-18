package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainService;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerBirthDate;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerEmail;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerFirstName;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerLastName;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerYearlyIncome;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.SpringQuoteSignedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class EventListenerQuoteSigned {

  private final ContractDomainService contractDomainService;

  @EventListener
  void on(SpringQuoteSignedEvent quoteSignedEvent) {
    log.info("Quote signed event received: {}", quoteSignedEvent);

    contractDomainService.fillOut(
        LeaseDuration.of(quoteSignedEvent.getLeaseDuration()),
        LeaseMileage.of(quoteSignedEvent.getLeaseMileage()),
        CustomerFirstName.of(quoteSignedEvent.getCustomerFirstName()),
        CustomerLastName.of(quoteSignedEvent.getCustomerLastName()),
        CustomerEmail.of(quoteSignedEvent.getCustomerEmail()),
        CustomerBirthDate.of(quoteSignedEvent.getCustomerBirthDate()),
        CustomerYearlyIncome.of(quoteSignedEvent.getCustomerYearlyIncome()),
        CarBrand.of(quoteSignedEvent.getCarBrand()),
        CarModel.of(quoteSignedEvent.getCarModel()),
        CarCatalogPrice.of(quoteSignedEvent.getCarCatalogPrice()),
        LeasePrice.of(quoteSignedEvent.getLeasePrice()),
        QuoteReference.of(quoteSignedEvent.getQuoteReference())
    );
  }
}