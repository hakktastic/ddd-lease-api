package nl.svb.dms.ddd_lease_api.sales.infrastructure.event;

import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.domain.event.QuoteFilledOutEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuoteEventsListener {

    @EventListener
    void handleQuoteFilledOut(QuoteFilledOutEvent event) {
        log.info("Handling quote filled out event: {}", event);
    }
}
