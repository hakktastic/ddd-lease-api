package nl.svb.dms.ddd_lease_api.sales.domain.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import org.jmolecules.event.annotation.DomainEvent;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
@DomainEvent
public class FillOutQuoteCommand implements SalesCommand {

  private final QuoteReference quoteReference;
}