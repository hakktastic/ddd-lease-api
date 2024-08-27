package nl.svb.dms.ddd_lease_api.sales.infrastructure.event;

import lombok.Getter;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import org.springframework.context.ApplicationEvent;

@Getter
public class SpringEventQuoteFilledOut extends ApplicationEvent {

    private final QuoteReference quoteReference;

    public SpringEventQuoteFilledOut(QuoteFilledOutEvent quoteFilledOutEvent, QuoteReference quoteReference) {
        super(quoteFilledOutEvent);
        this.quoteReference = quoteReference;
    }
}
