package nl.svb.dms.ddd_lease_api.sales.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.Quote;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.QuoteEntity;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerBirthDate;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerEmail;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerFirstName;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerLastName;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerSignature;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerYearlyIncome;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteProvider;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteStatus;
import nl.svb.dms.ddd_lease_api.sales.domain.command.CalculateInstallmentCommand;
import nl.svb.dms.ddd_lease_api.sales.domain.command.CommandResult;
import nl.svb.dms.ddd_lease_api.sales.domain.command.FillOutQuoteCommand;
import nl.svb.dms.ddd_lease_api.sales.domain.command.SignQuoteCommand;

@Slf4j
@RequiredArgsConstructor
public final class QuoteDomainService {

  private final QuoteProvider quoteProvider;
  private final QuoteDomainRepository quoteDomainRepository;

  public CommandResult fillOut(LeaseDuration duration, LeaseMileage mileage,
      CustomerFirstName customerFirstName,
      CustomerLastName customerLastName, CustomerEmail customerEmail,
      CustomerBirthDate customerBirthDate, CustomerYearlyIncome customerYearlyIncome,
      CarBrand brandName, CarModel model, CarCatalogPrice carCatalogPrice)
      throws QuoteNotFoundException {

    final var quoteReference = QuoteReference.of();
    final var quote = Quote.of(quoteReference,
        QuoteEntity.of(quoteReference, duration, mileage, customerFirstName,
            customerLastName, customerEmail, customerBirthDate, customerYearlyIncome, brandName,
            model,
            carCatalogPrice, LeasePrice.of(0.0), QuoteStatus.CREATED));

    final var fillOutQuoteResult = quote.handleCommand(FillOutQuoteCommand.of(QuoteReference.of()));
    return acceptSalesEventVisitor(fillOutQuoteResult);
  }

  public CommandResult calculateInstallment(QuoteReference quoteReference)
      throws QuoteNotFoundException {

    final var quote = quoteProvider.findQuote(quoteReference);
    final var calculateInstallmentResult = quote.handleCommand(CalculateInstallmentCommand.of());
    return acceptSalesEventVisitor(calculateInstallmentResult);
  }

  public CommandResult sign(QuoteReference quoteReference, CustomerSignature customerSignature)
      throws QuoteNotFoundException {

    final var quote = quoteProvider.findQuote(quoteReference);
    final var signQuoteResult = quote.handleCommand(SignQuoteCommand.of(customerSignature));
    return acceptSalesEventVisitor(signQuoteResult);

  }

  private CommandResult acceptSalesEventVisitor(CommandResult commandResult)
      throws QuoteNotFoundException {

    final var event = commandResult.getEvent();

    if (event != null) {
      log.debug("Accepting visitor for event: {}", event);
      event.accept(quoteDomainRepository);
    } else {
      log.warn("unable to find event for command: {}", commandResult);
    }

    return commandResult;
  }
}