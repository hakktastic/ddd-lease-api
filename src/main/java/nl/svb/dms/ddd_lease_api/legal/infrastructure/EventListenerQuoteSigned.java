package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainService;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class EventListenerQuoteSigned {

    private final ContractDomainService contractDomainService;

    @EventListener
    void on(QuoteSignedEvent quoteSignedEvent) {
        log.info("Quote signed event received: {}", quoteSignedEvent);

        final var quote = quoteSignedEvent.getQuote();
        final var quoteEntity = quote.getQuoteEntity();

        contractDomainService.fillOut(
                LeaseDuration.of(quoteEntity.getLeaseDuration().leaseDuration()),
                LeaseMileage.of(quoteEntity.getLeaseMileage().leaseMileage()),
                CustomerFirstName.of(quoteEntity.getCustomerFirstName().customerFirstName()),
                CustomerLastName.of(quoteEntity.getCustomerLastName().customerLastName()),
                CustomerEmail.of(quoteEntity.getCustomerEmail().customerEmail()),
                CustomerBirthDate.of(quoteEntity.getCustomerBirthDate().customerBirthDate()),
                CustomerYearlyIncome.of(quoteEntity.getCustomerYearlyIncome().customerYearlyIncome()),
                CarBrand.of(quoteEntity.getCarBrand().carBrand()),
                CarModel.of(quoteEntity.getCarModel().carModel()),
                CarCatalogPrice.of(quoteEntity.getCarCatalogPrice().carCatalogPrice()),
                LeasePrice.of(quoteEntity.getLeasePrice().leasePrice()),
                QuoteReference.of(quoteEntity.getQuoteReference().quoteReference())
        );
    }
}