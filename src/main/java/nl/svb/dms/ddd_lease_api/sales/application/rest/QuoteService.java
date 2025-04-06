package nl.svb.dms.ddd_lease_api.sales.application.rest;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainService;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.command.CommandResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class QuoteService {

    private final QuoteDomainService quoteDomainService;

    CommandResult fillOutQuote(FillOutQuoteRequest request) throws QuoteNotFoundException {

        return quoteDomainService.fillOut(
                LeaseDuration.of(request.leaseDuration()),
                LeaseMileage.of(request.mileage()),
                CustomerFirstName.of(request.customerFirstname()),
                CustomerLastName.of(request.customerLastname()),
                CustomerEmail.of(request.customerEmail()),
                CustomerBirthDate.of(request.customerBirthDate()),
                CustomerYearlyIncome.of(request.customerYearlyIncome()),
                CarBrand.of(request.carBrand()),
                CarModel.of(request.carModel()),
                CarCatalogPrice.of(request.carCatalogPrice()));
    }

    CommandResult calculateInstallment(UUID quoteReferenceUUID) throws QuoteNotFoundException {

        final var quoteReference = QuoteReference.of(quoteReferenceUUID);

        return quoteDomainService.calculateInstallment(quoteReference);
    }

    CommandResult signQuote(UUID quoteReferenceUUID, String signature) throws QuoteNotFoundException {

        final var quoteReference = QuoteReference.of(quoteReferenceUUID);
        final var customerSignature = CustomerSignature.of(signature);

        return quoteDomainService.sign(quoteReference, customerSignature);
    }
}