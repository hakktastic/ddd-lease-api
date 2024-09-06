package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class ContractEventsListener {

    @EventListener
    void handleQuoteFilledOut(QuoteFilledOutEvent event) {
        log.info("Handling quote filled out event: {}", event);
    }
}
