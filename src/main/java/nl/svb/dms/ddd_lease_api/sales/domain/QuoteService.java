package nl.svb.dms.ddd_lease_api.sales.domain;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.QuoteEntity;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.FillOutQuoteResult;
import nl.svb.dms.ddd_lease_api.sales.domain.command.FillOutQuote;

import java.util.Optional;

@RequiredArgsConstructor
public final class QuoteService {

    private final QuoteRepository quoteRepository;

    public Optional<FillOutQuoteResult> fillOut(LeaseDuration duration, LeaseMileage mileage, CustomerFirstName customerFirstName,
                                                CustomerLastName customerLastName, CustomerEmail customerEmail,
                                                CustomerBirthDate customerBirthDate, CustomerYearlyIncome customerYearlyIncome,
                                                CarBrand brandName, CarModel model, CarCatalogPrice carCatalogPrice) {


        final var quote = Quote.of(QuoteEntity.of(duration, mileage, customerFirstName, customerLastName, customerEmail,
                customerBirthDate, customerYearlyIncome, brandName, model, carCatalogPrice));
        
        final var fillOutQuoteResult = quote.handleCommand(FillOutQuote.of(duration, mileage, customerFirstName,
                customerLastName, customerEmail, customerBirthDate, customerYearlyIncome, brandName, model, carCatalogPrice));

        // handle result

        return null;
    }

    public void calculateInstallment() {

    }

    public void sign() {

    }
}
