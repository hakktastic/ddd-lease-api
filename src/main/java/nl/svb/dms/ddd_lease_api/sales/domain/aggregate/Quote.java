package nl.svb.dms.ddd_lease_api.sales.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteStatus;
import nl.svb.dms.ddd_lease_api.sales.domain.command.*;
import nl.svb.dms.ddd_lease_api.sales.domain.event.InstallmentCalculatedEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteSignedEvent;
import org.jmolecules.event.annotation.DomainEventHandler;

@Slf4j
@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public final class Quote {

    private final QuoteReference quoteReference;

    private final QuoteEntity quoteEntity;

    @DomainEventHandler
    public CommandResult handleCommand(final FillOutQuoteCommand fillOutQuoteCommand) {

        logCommand(fillOutQuoteCommand);

        if (!quoteEntity.customerHasMinimumRequiredAge()) {
            return rejectQuote(QuoteStatus.REJECTED_MIN_AGE);

        } else if (!quoteEntity.customerHasMinimumRequiredIncome()) {
            return rejectQuote(QuoteStatus.REJECTED_MIN_INCOME);

        }

        return fillOutQuote();
    }

    @DomainEventHandler
    public CommandResult handleCommand(final CalculateInstallmentCommand calculateInstallmentCommand) {

        logCommand(calculateInstallmentCommand);
        quoteEntity.calculateLeasePrice();

        if (!quoteEntity.installmentAmountIsAllowed()) {
            return rejectQuote(QuoteStatus.REJECTED_MAX_PERCENTAGE_OF_YEARLY_INCOME);
        }

        return calculateInstallmentForQuote();
    }

    @DomainEventHandler
    public CommandResult handleCommand(final SignQuoteCommand signQuoteCommand) {

        logCommand(signQuoteCommand);

        return signQuote();
    }

    private CommandResult fillOutQuote() {
        quoteEntity.setQuoteStatus(QuoteStatus.FILLED_OUT);
        return CommandResult.of(this, QuoteStatus.FILLED_OUT, new QuoteFilledOutEvent(this));
    }

    private CommandResult calculateInstallmentForQuote() {
        quoteEntity.setQuoteStatus(QuoteStatus.CALCULATED);
        return CommandResult.of(this, QuoteStatus.CALCULATED, new InstallmentCalculatedEvent(this));
    }

    private CommandResult signQuote() {
        quoteEntity.setQuoteStatus(QuoteStatus.SIGNED);
        return CommandResult.of(this, QuoteStatus.SIGNED, new QuoteSignedEvent(this));
    }

    private CommandResult rejectQuote(QuoteStatus reasonForRejection) {

        quoteEntity.setQuoteStatus(reasonForRejection);
        log.warn("quote rejected with reason: {}", reasonForRejection);

        return CommandResult.of(this, reasonForRejection, null);
    }

    private void logCommand(SalesCommand salesCommand) {
        log.debug("handling command: {}", salesCommand.toString());
    }
}