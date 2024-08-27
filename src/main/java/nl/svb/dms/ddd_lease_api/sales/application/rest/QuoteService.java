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
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteDomainService quoteDomainService;

    public QuoteResponse fillOutQuote(FillOutQuoteRequest request) throws QuoteNotFoundException {

        final var fillOutQuoteResult = quoteDomainService.fillOut(
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

        final var quoteStatus = fillOutQuoteResult.getStatus();
        final var quoteReferenceUUID = fillOutQuoteResult.getQuote().getQuoteReference().quoteReference();

        return QuoteResponse.of(getHttpStatusFrom(quoteStatus), quoteReferenceUUID, quoteStatus,
                createUri(quoteReferenceUUID));
    }

    public QuoteResponse calculateInstallment(UUID quoteReferenceUUID) throws QuoteNotFoundException {

        final var quoteReference = QuoteReference.of(quoteReferenceUUID);
        final var calculateInstallmentResult = quoteDomainService.calculateInstallment(quoteReference);
        final var quoteStatus = calculateInstallmentResult.getStatus();

        return QuoteResponse.of(getHttpStatusFrom(quoteStatus), quoteReferenceUUID, quoteStatus,
                createUri(quoteReferenceUUID));
    }

    public QuoteResponse signQuote(UUID quoteReferenceUUID, String signature) throws QuoteNotFoundException {

        final var quoteReference = QuoteReference.of(quoteReferenceUUID);
        final var customerSignature = CustomerSignature.of(signature);
        final var signQuoteResult = quoteDomainService.sign(quoteReference, customerSignature);
        final var quoteStatus = signQuoteResult.getStatus();

        return QuoteResponse.of(getHttpStatusFrom(quoteStatus), quoteReferenceUUID, quoteStatus,
                createUri(quoteReferenceUUID));
    }

    private HttpStatus getHttpStatusFrom(QuoteStatus quoteStatus) {
        return (quoteStatus == QuoteStatus.FILLED_OUT) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    private URI createUri(UUID quoteReferenceUUID) {
        return URI.create("/api/v1/quotes/" + quoteReferenceUUID);
    }
}
